package io.github.nefelibatahahaha.knowflow.mapper;

import io.github.nefelibatahahaha.knowflow.model.entity.Agent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author charon
 * @description 针对表【agent】的数据库操作Mapper
 * @createDate 2025-12-02 14:48:44
 * @Entity io.github.nefelibatahahaha.knowflow.model.entity.Agent
 */
@Mapper
public interface AgentMapper {
    int insert(Agent agent);

    Agent selectById(String id);

    List<Agent> selectAll();

    int deleteById(String id);

    int updateById(Agent agent);
}
