package me.batizhao.terrace.vo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * <p> 流程环节配置 </p>
 *
 * @author wws
 * @since 2020-08-07 11:14
 */
@Data
@Schema(description = "流程环节配置对象")
public class ProcessNodeConfig {

    /**
     * 主键Id
     **/
    private String id;

    /**
     * 流程定义Id
     */
    @Schema(description = "流程定义Id", name = "processDefId")
    @NotNull(message = "流程定义Id不能为空")
    private String processDefId;

    /**
     * 流程环节Key
     */
    @Schema(description = "流程环节Key", name = "taskDefKey")
    @NotNull(message = "流程环节Key不能为空")
    private String taskDefKey;

    /**
     * 业务系统Id
     */
    @Schema(description = "业务系统Id", name = "businessId")
    @NotNull(message = "业务系统Id不能为空")
    private String businessId;

    /**
     * 配置信息
     */
    @Schema(description = "配置信息", name = "config")
    @JsonDeserialize(using = NodeConfigDeserializer.class)
    private NodeConfig config;

    static class NodeConfigDeserializer extends JsonDeserializer<NodeConfig> {

        @Override
        public NodeConfig deserialize(JsonParser jp, DeserializationContext ctx)
                throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);

            String text = node.toString();
            String trimmed = text.substring(1, text.length() - 1);
            trimmed = trimmed.replace("\\", "");
            trimmed = trimmed.replace("\n", "");

            ObjectMapper mapper = new ObjectMapper();
            //允许使用单引号
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //解决序列化空对象问题
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            JsonNode obj = mapper.readTree(trimmed);

            return mapper.convertValue(obj, NodeConfig.class);
        }
    }

}
