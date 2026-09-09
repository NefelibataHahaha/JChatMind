package io.github.nefelibatahahaha.knowflow.event.listener;

import io.github.nefelibatahahaha.knowflow.agent.AgentRuntime;
import io.github.nefelibatahahaha.knowflow.agent.AgentRuntimeFactory;
import io.github.nefelibatahahaha.knowflow.event.ChatEvent;
import io.github.nefelibatahahaha.knowflow.message.SseMessage;
import io.github.nefelibatahahaha.knowflow.service.SseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ChatEventListener {

    private final AgentRuntimeFactory agentRuntimeFactory;
    private final SseService sseService;

    @Async
    @EventListener
    public void handle(ChatEvent event) {
        try {
            // 创建一个 Agent 实例处理聊天事件
            AgentRuntime agentRuntime = agentRuntimeFactory.create(event.getAgentId(), event.getSessionId());
            agentRuntime.run();
        } catch (Exception e) {
            log.error("处理聊天事件失败，sessionId={}", event.getSessionId(), e);

            SseMessage errorMessage = SseMessage.builder()
                    .type(SseMessage.Type.AI_ERROR)
                    .payload(SseMessage.Payload.builder()
                            .statusText("本轮处理失败，请稍后重试")
                            .done(true)
                            .build())
                    .build();

            try {
                sseService.send(event.getSessionId(), errorMessage);
            } catch (Exception sendException) {
                log.warn("发送失败通知失败，sessionId={}",
                        event.getSessionId(), sendException);
            }
        }
    }
}
