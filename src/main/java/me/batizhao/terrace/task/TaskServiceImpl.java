package me.batizhao.terrace.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import me.batizhao.terrace.api.TaskService;
import me.batizhao.terrace.api.TerraceApi;
import me.batizhao.terrace.dto.AppTodoTaskDTO;
import me.batizhao.terrace.dto.StartProcessDTO;
import me.batizhao.terrace.dto.SubmitProcessDTO;
import me.batizhao.terrace.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批接口实现类
 *
 * @author batizhao
 * @since 2021-06-10
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TerraceApi terraceApi;

    @Override
    public InitProcessDefView findProcessDefinitionByKey(String key) {
        return terraceApi.loadProcessDefinitionByKey(key).getData();
    }

    @Override
    public IPage<TodoTaskView> findTodoTasks(Page page, AppTodoTaskDTO appTodoTaskDTO) {
        return terraceApi.loadTodoTasks(page.getCurrent(), page.getSize(),
                appTodoTaskDTO.getUserName(), appTodoTaskDTO.getBusinessModuleId(), appTodoTaskDTO.getQueryType(),
                appTodoTaskDTO.getStatus(), appTodoTaskDTO.getType(), appTodoTaskDTO.getTitle()).getData();
    }

    @Override
    public IPage<TodoTaskView> findDoneTasks(Page page, AppTodoTaskDTO appTodoTaskDTO) {
        return terraceApi.loadDoneTask(page.getCurrent(), page.getSize(),
                appTodoTaskDTO.getCode(), appTodoTaskDTO.getTitle(), appTodoTaskDTO.getRealName(),
                appTodoTaskDTO.getUserName(), appTodoTaskDTO.getTaskName(), appTodoTaskDTO.getType(),
                appTodoTaskDTO.getBusinessModuleId()).getData();
    }

    @Override
    public TaskNodeView findById(Long id) {
        return terraceApi.loadTaskDetail(id.toString(), "0").getData();
    }

    @Override
    public String start(StartProcessDTO dto) {
        return terraceApi.start(dto).getData();
    }

    @Override
    public String submit(SubmitProcessDTO dto) {
        return terraceApi.submit(dto).getData();
    }

    @Override
    public List<ProcessRouterView> findProcessRouter(String processDefinitionId, String taskDefKey) {
        return terraceApi.loadProcessRouter(taskDefKey, processDefinitionId).getData();
    }

    @Override
    public List<ProcessMessageView> loadMessage(String procInstId, List<String> taskDefKeyList, Integer orderRule) {
        return terraceApi.loadMessage(procInstId, taskDefKeyList, orderRule).getData();
    }

    @Override
    public Boolean sign(String taskId, String type, String userId) {
        return terraceApi.sign(taskId, type, userId).getData();
    }

    @Override
    public List<QueryCandidateView> loadCandidate(String processInstId, String taskDefKey, String taskId, Boolean back, String processDefId, String orgId) {
        return terraceApi.loadCandidate(processInstId, taskDefKey, taskId, back, processDefId, orgId).getData();
    }
}
