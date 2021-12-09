package me.batizhao.terrace.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p> 任务视图列表 </p>
 *
 * @author wws
 * @since 2020-08-22 10:51
 */
@Schema(description = "任务视图列表")
@Data
public class TodoTaskView {

    /**
     * 任务Id
     */
    @Schema(description = "任务Id", name = "taskId")
    private String taskId;

    @Schema(description = "任务类型", name = "任务类型：0 待办任务 1 待阅任务")
    private String type;

    /**
     * 业务数据Id
     */
    @Schema(description = "业务数据Id", name = "appId")
    private String appId;

    /**
     * 流程实例Id
     */
    @Schema(description = "流程实例Id", name = "procInstId")
    private String procInstId;

    /**
     * 流程定义Id
     */
    @Schema(description = "流程定义Id", name = "procDefId")
    private String procDefId;

    /**
     * 流程定义key
     */
    @Schema(description = "流程定义key", name = "key")
    private String key;

    /**
     * 流程环节Key
     */
    @Schema(description = "流程环节Key", name = "taskDefKey")
    private String taskDefKey;

    /**
     * 流程环节名称
     */
    @Schema(description = "流程环节名称", name = "taskName")
    private String taskName;

    /**
     * 业务数据编号
     */
    @Schema(description = "业务数据编号", name = "code")
    private String code;

    /**
     * 业务数据标题
     */
    @Schema(description = "业务数据标题", name = "title")
    private String title;

    /**
     * 起草人
     */
    @Schema(description = "起草人", name = "createName")
    private String createName;

    /**
     * 起草部门
     */
    @Schema(description = "起草部门", name = "createOrgName")
    private String createOrgName;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", name = "userId")
    private String userId;

    /**
     * 任务创建时间
     */
    @Schema(description = "任务创建时间", name = "createTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

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

    /**
     * 模块请求路径
     */
    @Schema(description = "模块请求路径", name = "url")
    private String url;

    /**
     * 发件人
     */
    @Schema(description = "发件人", name = "oldUser")
    private String oldUser;

    /**
     * 待办人列表
     */
    @Schema(description = "待办人列表", name = "todoUserList")
    private List<String> todoUserList;

    /**
     * 在办人列表
     */
    @Schema(description = "在办人列表", name = "doingUserList")
    private List<String> doingUserList;

    /**
     * 任务签收人
     */
    @Schema(description = "任务签收人", name = "signer")
    private String signer;

    @Schema(description = "任务签收时间", name = "claimTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String claimTime;

    @Schema(description = "任务结束时间", name = "endTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
}
