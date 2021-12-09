package me.batizhao.terrace.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * <p> 流转意见视图类 </p>
 *
 * @author wws
 * @since 2020-10-20 14:33
 */
@Data
@Schema(description = "流转意见视图类")
public class ProcessMessageView {

    /**
     * 流程实例Id
     **/
    @Schema(description = "流程定义id ", name = "processInstId")
    private String processInstId;

    /**
     * 环节名称
     **/
    @Schema(description = "环节名称 ", name = "taskDefKey")
    private String taskDefKey;

    /**
     * 环节名
     **/
    @Schema(description = "环节名 ", name = "taskName")
    private String taskName;

    /**
     * 用户名
     **/
    @Schema(description = "用户名 ", name = "userId")
    private String userId;

    /**
     * 姓名
     **/
    @Schema(description = "姓名 ", name = "userName")
    private String userName;

    /**
     * 组织名
     **/
    @Schema(description = "组织名 ", name = "orgName")
    private String orgName;

    /**
     * 角色名
     **/
    @Schema(description = "角色名 ", name = "roleName")
    private String roleName;

    /**
     * 意见
     **/
    @Schema(description = "意见 ", name = "message")
    private String message;

    @Schema(description = "组织Id ", name = "orgId")
    private String orgId;

    @Schema(description = "角色id ", name = "roleId")
    private String roleId;

    /**
     * 创建时间
     **/
    @Schema(description = "创建时间 ", name = "createTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
