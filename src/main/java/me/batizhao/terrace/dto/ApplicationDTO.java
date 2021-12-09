package me.batizhao.terrace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 业务系统应用核心数据封装 </p>
 *
 * @author wws
 * @since 2020-08-11 14:04
 */
@Data
@NoArgsConstructor
@Schema(description = "业务系统应用核心数据封装")
public class ApplicationDTO {

    /**
     * 业务数据Id
     */
    @Schema(description = "业务数据Id", name = "id")
    private String id;

    /**
     * 业务标题
     */
    @Schema(description = "业务标题", name = "title")
    private String title;

    /**
     * 业务数据编码
     */
    @Schema(description = "业务数据编码", name = "code")
    private String code;

    /**
     * 创建人
     */
    @Schema(description = "创建人", name = "creator")
    private String creator;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称", name = "moduleName")
    private String moduleName;

    /**
     * 业务系统模块ID
     */
    @Schema(description = "业务系统模块ID", name = "moduleId")
    private String moduleId;
}
