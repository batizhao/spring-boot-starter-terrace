# terrace-sdk-boot-starter
在 Spring Boot 2.5.4 上通过测试。

Maven

```xml
<dependency>
   <groupId>me.batizhao</groupId>
   <artifactId>terrace-sdk-boot-starter</artifactId>
   <version>1.1</version>
</dependency>
```

application.yml

```yaml
client:
  terrace:
    enabled: true
    client-id: xxx
    client-secret: xxx
    key-token: "terrace:token:data"
    key-expire: "terrace:token:expire"
    url: http://172.31.21.208:8886/terrace/
    # local or redis
    token-store-location: local
```

Java

```java
@Autowired
private TerraceApi terraceApi;
```

API

```java
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
 * 任务获取
 *
 * @param dto
 * @return
 */
@RequestLine("GET oa/task/todo")
R<Page<TodoTaskView>> loadTasks(AppTodoTaskDTO dto);

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
```

