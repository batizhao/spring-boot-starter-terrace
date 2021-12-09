package me.batizhao.terrace.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * <p> 对接待办任务查询参数 </p>
 *
 * @author wws
 * @since 2021-05-18 10:54
 */
@Schema(description = "对接待办任务查询参数")
public class AppTodoTaskDTO extends TodoTask implements Serializable {

    @Schema(description = "第三方业务系统模块所属Id", name = "businessModuleId")
    private String businessModuleId;

    public AppTodoTaskDTO() {
        super.setQueryType("0");
    }

    public String getBusinessModuleId() {
        return businessModuleId;
    }

    public void setBusinessModuleId(String businessModuleId) {
        this.businessModuleId = businessModuleId;
    }
}
