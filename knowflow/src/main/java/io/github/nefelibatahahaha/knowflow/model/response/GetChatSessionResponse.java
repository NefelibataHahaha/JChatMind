package io.github.nefelibatahahaha.knowflow.model.response;

import io.github.nefelibatahahaha.knowflow.model.vo.ChatSessionVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetChatSessionResponse {
    private ChatSessionVO chatSession;
}
