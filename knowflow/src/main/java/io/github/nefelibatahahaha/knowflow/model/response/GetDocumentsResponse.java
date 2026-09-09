package io.github.nefelibatahahaha.knowflow.model.response;

import io.github.nefelibatahahaha.knowflow.model.vo.DocumentVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetDocumentsResponse {
    private DocumentVO[] documents;
}

