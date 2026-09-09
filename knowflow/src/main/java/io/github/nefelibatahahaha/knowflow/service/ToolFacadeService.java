package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.agent.tools.Tool;

import java.util.List;

public interface ToolFacadeService {
    List<Tool> getAllTools();

    List<Tool> getOptionalTools();

    List<Tool> getFixedTools();
}
