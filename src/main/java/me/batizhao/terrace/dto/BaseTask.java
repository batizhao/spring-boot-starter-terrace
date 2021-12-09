package me.batizhao.terrace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> 任务查询参数基础类 </p>
 *
 * @author wws
 * @since 2021-05-18 09:18
 */
@NoArgsConstructor
@Schema(description = "任务查询参数基础类")
public class BaseTask implements Serializable {

    /**
     * 任务类型 所有任务
     **/
    public final static String TASK_ALL_TYPE = "0";
    /**
     * 任务类型 流程审批任务
     **/
    public final static String TASK_CHECK_TYPE = "1";
    /**
     * 任务类型 流程传阅任务
     **/
    public final static String TASK_CIR_TYPE = "2";

    /**
     * 任务类型 所有任务状态
     **/
    public final static String TASK_ALL_STATUS = "0";
    /**
     * 任务类型 待办（带阅）任务
     **/
    public final static String TASK_TODO_STATUS = "1";
    /**
     * 任务类型 在办（在阅）任务
     **/
    public final static String TASK_DOING_STATUS = "2";

    /**
     * 任务状态类型 待办任务
     **/
    public final static String TASK_TYPE_TODO = "0";
    /**
     * 任务状态类型 已办任务
     **/
    public final static String TASK_TYPE_DONE = "1";

    /**
     * 对接接口
     **/
    public final static int TASK_QUERY_TYPE_APP = 0;
    /**
     * 平台接口
     **/
    public final static int TASK_QUERY_TYPE_MGR = 1;

    @Schema(description = "业务系统Id", name = "businessId", hidden = true)
    private String businessId;

    @Schema(description = "正文编号", name = "code")
    private String code;

    @Schema(description = "正文标题", name = "title")
    private String title;

    @Schema(description = "姓名", name = "realName")
    private String realName;

    @Schema(description = "登录名", name = "userName")
    private String userName;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
