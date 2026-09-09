package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.model.request.CreateAgentRequest;
import io.github.nefelibatahahaha.knowflow.model.request.UpdateAgentRequest;
import io.github.nefelibatahahaha.knowflow.model.response.CreateAgentResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetAgentsResponse;

public interface AgentFacadeService {
    GetAgentsResponse getAgents();

    CreateAgentResponse createAgent(CreateAgentRequest request);

    void deleteAgent(String agentId);

    void updateAgent(String agentId, UpdateAgentRequest request);
}
