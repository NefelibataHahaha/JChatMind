package io.github.nefelibatahahaha.knowflow.model.request;

import io.github.nefelibatahahaha.knowflow.model.dto.ChatMessageDTO;
import lombok.Data;

@Data
public class UpdateChatMessageRequest {
    private String content;
    private ChatMessageDTO.MetaData metadata;
}

