package io.github.nefelibatahahaha.knowflow.model.response;

import io.github.nefelibatahahaha.knowflow.model.vo.ChatSessionVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetChatSessionsResponse {
    private ChatSessionVO[] chatSessions;
}
