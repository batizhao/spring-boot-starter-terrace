package me.batizhao.terrace.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import feign.Param;
import feign.RequestLine;
import me.batizhao.terrace.dto.R;
import me.batizhao.terrace.dto.StartProcessDTO;
import me.batizhao.terrace.dto.SubmitProcessDTO;
import me.batizhao.terrace.vo.*;

import java.util.List;

/**
 * 流程平台接口定义
 *
 * @author batizhao
 * @date 2021/6/11
 */
public interface TerraceApi {

    /**
     * 通过流程定义 key 获取最新流程定义，并初始化流程起始环节配置
     *
     * @param key
     * @return
     */
    @RequestLine("GET oa/repository/process/definition/{key}")
    R<InitProcessDefView> loadProcessDefinitionByKey(@Param("key") String key);

    /**
     * 流程启动
     *
     * @param dto
     * @return
     */
    @RequestLine("POST oa/runtime/start")
    R<String> start(StartProcessDTO dto);

    /**
     * 待办任务获取
     *
     * @param pageIndex 当前页
     * @param pageSize 每页条数
     * @param userName
     * @param businessModuleId
     * @param queryType
     * @param status 任务状态 0 所有状态 1、待办（待阅）任务 2、在办（在阅读）任务
     * @param type 任务类型 0 所有任务 1、审核任务 2、传阅任务
     * @return
     */
    @RequestLine("GET oa/task/todo?pageIndex={pageIndex}&pageSize={pageSize}&userName={userName}&businessModuleId={businessModuleId}&queryType={queryType}&status={status}&type={type}&title={title}")
    R<Page<TodoTaskView>> loadTodoTasks(@Param("pageIndex") Long pageIndex,
                                        @Param("pageSize") Long pageSize,
                                        @Param("userName") String userName,
                                        @Param("businessModuleId") String businessModuleId,
                                        @Param("queryType") String queryType,
                                        @Param("status") Integer status,
                                        @Param("type") String type,
                                        @Param("title") String title);

    /**
     * 已办任务获取
     * @param pageIndex 当前页
     * @param pageSize 每页条数
     * @param code 正文编号
     * @param title 正文标题
     * @param realName 姓名
     * @param userName 登录名
     * @param taskName 所属任务环节（任务状态）
     * @param type 任务类型 0 所有任务 1、审核任务 2、传阅任务
     * @param businessModuleId 第三方业务系统模块所属Id
     * @return
     */
    @RequestLine("GET /oa/task/done?pageIndex={pageIndex}&pageSize={pageSize}&code={code}&title={title}&realName={realName}&userName={userName}&taskName={taskName}&type={type}&businessModuleId={businessModuleId}")
    R<Page<TodoTaskView>> loadDoneTask(@Param("pageIndex") Long pageIndex,
                                       @Param("pageSize") Long pageSize,
                                       @Param("code") String code,
                                       @Param("title") String title,
                                       @Param("realName") String realName,
                                       @Param("userName") String userName,
                                       @Param("taskName") String taskName,
                                       @Param("type") String type,
                                       @Param("businessModuleId") String businessModuleId);

    /**
     * 通过流程任务id获取任务信息
     *
     * @param taskId 任务Id
     * @param type 任务类型：0 审批任务、 1 传阅任务
     * @return
     */
    @RequestLine("GET oa/task/{taskId}/{type}")
    R<TaskNodeView> loadTaskDetail(@Param("taskId") String taskId, @Param("type") String type);

    /**
     * 获取环节的输出路由及路由后的任务环节配置信息
     *
     * @param id 流程定义Id
     * @param taskDefKey 流程环节key
     * @return
     */
    @RequestLine("GET /oa/repository/{id}/{taskDefKey}/next/rout")
    R<List<ProcessRouterView>> loadProcessRouter(@Param("taskDefKey") String taskDefKey, @Param("id") String id);

    /**
     * 流程提交
     *
     * @param dto
     * @return
     */
    @RequestLine("POST oa/runtime/submit")
    R<String> submit(SubmitProcessDTO dto);

    /**
     * 获取流程指定环节意见
     * @param procInstId
     * @param taskDefKeyList
     * @param orderRule
     * @return
     */
    @RequestLine("GET /oa/history/{procInstId}/process/message?taskDefKeyList={taskDefKeyList}&orderRule={orderRule}")
    R<List<ProcessMessageView>> loadMessage(@Param("procInstId") String procInstId,
                                            @Param("taskDefKeyList") List<String> taskDefKeyList,
                                            @Param("orderRule") Integer orderRule);

    /**
     * 签收
     * @param taskId 任务Id
     * @param type 签收人
     * @param userId 任务类型：0 审批任务、 1 传阅任务
     * @return
     */
    @RequestLine("POST /oa/task/{taskId}/{type}/{userId}/sign")
    R<Boolean> sign(@Param("taskId") String taskId,
                    @Param("type") String type,
                    @Param("userId") String userId);

    /**
     * 解签
     * @param taskId 任务Id
     * @param type 任务类型：0 审批任务、 1 传阅任务
     * @param userId 签收人
     * @return
     */
    @RequestLine("POST /oa/task/{taskId}/{type}/{userId}/unsigned")
    R<Boolean> unsigned(@Param("taskId") String taskId,
                        @Param("type") String type,
                        @Param("userId") String userId);
}
