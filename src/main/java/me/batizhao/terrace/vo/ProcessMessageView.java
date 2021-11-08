package me.batizhao.terrace.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p> 流转意见视图类 </p>
 *
 * @author wws
 * @since 2020-10-20 14:33
 */
@Data
@ApiModel(value = "流转意见视图类", description = "流转意见视图类")
public class ProcessMessageView {

    /**
     * 流程实例Id
     **/
    @ApiModelProperty(value = "流程定义id ", name = "processInstId")
    private String processInstId;

    /**
     * 环节名称
     **/
    @ApiModelProperty(value = "环节名称 ", name = "taskDefKey")
    private String taskDefKey;

    /**
     * 环节名
     **/
    @ApiModelProperty(value = "环节名 ", name = "taskName")
    private String taskName;

    /**
     * 用户名
     **/
    @ApiModelProperty(value = "用户名 ", name = "userId")
    private String userId;

    /**
     * 姓名
     **/
    @ApiModelProperty(value = "姓名 ", name = "userName")
    private String userName;

    /**
     * 组织名
     **/
    @ApiModelProperty(value = "组织名 ", name = "orgName")
    private String orgName;

    /**
     * 角色名
     **/
    @ApiModelProperty(value = "角色名 ", name = "roleName")
    private String roleName;

    /**
     * 意见
     **/
    @ApiModelProperty(value = "意见 ", name = "message")
    private String message;

    @ApiModelProperty(value = "组织Id ", name = "orgId")
    private String orgId;

    @ApiModelProperty(value = "角色id ", name = "roleId")
    private String roleId;

    /**
     * 创建时间
     **/
    @ApiModelProperty(value = "创建时间 ", name = "createTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
