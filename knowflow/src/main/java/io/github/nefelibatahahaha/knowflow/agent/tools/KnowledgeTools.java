package io.github.nefelibatahahaha.knowflow.agent.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nefelibatahahaha.knowflow.service.RagService;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import java.util.List;

@Component
public class KnowledgeTools implements Tool {

    private final RagService ragService;
    private final ObjectMapper objectMapper;

    public KnowledgeTools(RagService ragService, ObjectMapper objectMapper) {
        this.ragService = ragService;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "KnowledgeTool";
    }

    @Override
    public String getDescription() {
        return "用于从知识库执行语义检索（RAG）。输入知识库 ID 和查询文本，返回与查询最相关的内容片段。";
    }

    @Override
    public ToolType getType() {
        return ToolType.FIXED;
    }

    @org.springframework.ai.tool.annotation.Tool(
            name = "KnowledgeTool",
            description = "从指定知识库中执行相似性检索（RAG）。参数为知识库 ID（kbsId）和查询文本（query），返回与查询最相关的知识片段。"
    )
    public String knowledgeQuery(String kbsId, String query) {
        List<String> matches = ragService.similaritySearch(kbsId, query);

        Map<String,Object> result = Map.of(
                "matches",matches,
                "message",matches.isEmpty()?"知识库中没有可用内容":"已检索到"+matches.size()+"条知识片段"
        );

        try{
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("知识库检索结果序列化失败",e);
        }
    }
}
