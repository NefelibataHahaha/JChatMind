package io.github.nefelibatahahaha.knowflow.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiChatClientConfig {
    // deepseek
    @Bean("deepseek-v4-flash")
    public ChatClient deepSeekV4FlashChatClient(
            DeepSeekChatModel deepSeekChatModel) {
        return ChatClient.builder(deepSeekChatModel)
                .defaultOptions(DeepSeekChatOptions.builder()
                        .model("deepseek-v4-flash")
                        .internalToolExecutionEnabled(false)
                        .build())
                .build();
    }

    @Bean("deepseek-v4-pro")
    public ChatClient deepSeekV4ProChatClient(
            DeepSeekChatModel deepSeekChatModel) {
        return ChatClient.builder(deepSeekChatModel)
                .defaultOptions(DeepSeekChatOptions.builder()
                        .model("deepseek-v4-pro")
                        .internalToolExecutionEnabled(false)
                        .build())
                .build();
    }

    // zhipuai
    @Bean("glm-4.6")
    public ChatClient zhiPuAiChatClient(ZhiPuAiChatModel zhiPuAiChatModel) {
        return ChatClient.builder(zhiPuAiChatModel)
                .defaultOptions(ZhiPuAiChatOptions.builder()
                        .model("glm-4.6")
                        .internalToolExecutionEnabled(false)
                        .build())
                .build();
    }
}
