package me.batizhao.terrace.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> 待办任务查询参数 </p>
 *
 * @author wws
 * @since 2021-05-18 09:29
 */
@NoArgsConstructor
@ApiModel(value = "待办任务查询参数", description = "待办任务查询参数")
public class TodoTask extends BaseTask implements Serializable {

    @ApiModelProperty(value = "任务状态 0 所有状态 1、待办（待阅）任务 2、在办（在阅读）任务", name = "status")
    private Integer status;

    @ApiModelProperty(value = "所属任务环节（任务状态）", name = "taskName")
    private String taskName;

    @ApiModelProperty(value = "查询类型 : 0、对接平台接口查询 1、流程平台管理端查询", name = "queryType", hidden = true)
    private String queryType;

    @ApiModelProperty(value = "任务类型 0 所有任务 1、审核任务 2、传阅任务", name = "type")
    private String type;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
