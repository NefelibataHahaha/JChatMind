package io.github.nefelibatahahaha.knowflow.model.response;

import io.github.nefelibatahahaha.knowflow.model.vo.KnowledgeBaseVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetKnowledgeBasesResponse {
    private KnowledgeBaseVO[] knowledgeBases;
}

