package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.model.request.CreateKnowledgeBaseRequest;
import io.github.nefelibatahahaha.knowflow.model.request.UpdateKnowledgeBaseRequest;
import io.github.nefelibatahahaha.knowflow.model.response.CreateKnowledgeBaseResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetKnowledgeBasesResponse;

public interface KnowledgeBaseFacadeService {
    GetKnowledgeBasesResponse getKnowledgeBases();

    CreateKnowledgeBaseResponse createKnowledgeBase(CreateKnowledgeBaseRequest request);

    void deleteKnowledgeBase(String knowledgeBaseId);

    void updateKnowledgeBase(String knowledgeBaseId, UpdateKnowledgeBaseRequest request);
}

