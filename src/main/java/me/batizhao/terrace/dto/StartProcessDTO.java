package me.batizhao.terrace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 流程启动参数 </p>
 *
 * @author wws
 * @since 2020-08-16 20:09
 */
@Data
@NoArgsConstructor
@Schema(description = "流程启动参数")
public class StartProcessDTO extends ProcessDTO {

    /**
     * 是否生成草稿任务
     **/
    @Schema(description = "是否生成草稿任务, 默认值：false", name = "draft")
    private Boolean draft;

    @Schema(description = "流程实例id", name = "procInstId")
    private String procInstId;
}
