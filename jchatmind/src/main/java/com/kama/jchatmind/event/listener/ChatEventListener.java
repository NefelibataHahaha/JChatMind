package com.kama.jchatmind.event.listener;

import com.kama.jchatmind.agent.JChatMind;
import com.kama.jchatmind.agent.JChatMindFactory;
import com.kama.jchatmind.event.ChatEvent;
import com.kama.jchatmind.message.SseMessage;
import com.kama.jchatmind.service.SseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ChatEventListener {

    private final JChatMindFactory jChatMindFactory;
    private final SseService sseService;

    @Async
    @EventListener
    public void handle(ChatEvent event) {
        try {
            // 创建一个 Agent 实例处理聊天事件
            JChatMind jChatMind = jChatMindFactory.create(event.getAgentId(), event.getSessionId());
            jChatMind.run();
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
