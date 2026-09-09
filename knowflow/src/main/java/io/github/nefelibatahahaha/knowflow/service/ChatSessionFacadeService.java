package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.model.request.CreateChatSessionRequest;
import io.github.nefelibatahahaha.knowflow.model.request.UpdateChatSessionRequest;
import io.github.nefelibatahahaha.knowflow.model.response.CreateChatSessionResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetChatSessionResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetChatSessionsResponse;

public interface ChatSessionFacadeService {
    GetChatSessionsResponse getChatSessions();

    GetChatSessionResponse getChatSession(String chatSessionId);

    GetChatSessionsResponse getChatSessionsByAgentId(String agentId);

    CreateChatSessionResponse createChatSession(CreateChatSessionRequest request);

    void deleteChatSession(String chatSessionId);

    void updateChatSession(String chatSessionId, UpdateChatSessionRequest request);
}
