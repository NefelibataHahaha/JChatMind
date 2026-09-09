package io.github.nefelibatahahaha.knowflow.controller;

import io.github.nefelibatahahaha.knowflow.agent.tools.Tool;
import io.github.nefelibatahahaha.knowflow.model.common.ApiResponse;
import io.github.nefelibatahahaha.knowflow.service.ToolFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ToolController {

    private final ToolFacadeService toolFacadeService;

    // 给前端提供的可选的工具列表
    @GetMapping("/tools")
    public ApiResponse<List<Tool>> getOptionalTools() {
        return ApiResponse.success(toolFacadeService.getOptionalTools());
    }
}
