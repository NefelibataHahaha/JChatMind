package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.model.dto.ChatMessageDTO;
import io.github.nefelibatahahaha.knowflow.model.request.CreateChatMessageRequest;
import io.github.nefelibatahahaha.knowflow.model.request.UpdateChatMessageRequest;
import io.github.nefelibatahahaha.knowflow.model.response.CreateChatMessageResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetChatMessagesResponse;

import java.util.List;

public interface ChatMessageFacadeService {
    GetChatMessagesResponse getChatMessagesBySessionId(String sessionId);

    List<ChatMessageDTO> getChatMessagesBySessionIdRecently(String sessionId, int limit);

    CreateChatMessageResponse createChatMessage(CreateChatMessageRequest request);

    CreateChatMessageResponse createChatMessage(ChatMessageDTO chatMessageDTO);

    CreateChatMessageResponse agentCreateChatMessage(CreateChatMessageRequest request);

    CreateChatMessageResponse appendChatMessage(String chatMessageId, String appendContent);

    void deleteChatMessage(String chatMessageId);

    void updateChatMessage(String chatMessageId, UpdateChatMessageRequest request);
}
