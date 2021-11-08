package me.batizhao.terrace.dto;

import lombok.Data;

/**
 * 流程定义（模版）POJO类
 *
 * @author batizhao
 * @date 2021/6/23
 */
@Data
public class ProcessDefinitionDTO {


    /**
     * 流程定义id
     */
    private String id;

    /**
     * 流程分类
     */
    private String category;

    /**
     * 流程定义名称
     */
    private String name;

    /**
     * 流程定义key
     */
    private String key;

    /**
     * 版本号
     */
    private int version;

    /**
     * 部署id
     */
    private String deploymentId;

}
