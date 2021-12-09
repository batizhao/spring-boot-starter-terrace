package me.batizhao.terrace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import me.batizhao.terrace.dto.NodeDTO;

/**
 * <p> 流程环节配置视图信息 </p>
 *
 * @author wws
 * @since 2020-08-24 14:39
 */
@Schema(description = "流程环节配置视图信息")
@Data
public class NodeView {

    /**
     * 流程环节信息
     **/
    @Schema(description = "流程环节信息", name = "dto")
    private NodeDTO dto;

    /**
     * 流程环节配置
     **/
    @Schema(description = "流程环节配置", name = "config")
    private ProcessNodeConfig config;
}
