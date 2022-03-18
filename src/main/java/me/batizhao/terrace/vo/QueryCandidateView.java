package me.batizhao.terrace.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p> 流程候选人查询参数 </p>
 *
 * @author wws
 * @since 2020-09-14 13:20
 */
@Data
@Schema(description = "流程候选人查询参数")
public class QueryCandidateView {

    @Schema(description = "候选人登陆名", name = "userId", required = true)
    private String userId;

    @Schema(description = "候选人姓名", name = "userName")
    private String userName;

    @Schema(description = "候选人所属组织id", name = "orgId")
    private String orgId;

    @Schema(description = "候选人所属组织名称", name = "orgId")
    private String orgName;

    @Schema(description = "候选人所属角色id", name = "roleId")
    private String roleId;

    @Schema(description = "候选人所属角色名", name = "roleName")
    private String roleName;

    @Schema(description = "候选人的委托人id", name = "principal")
    private String principal;

    @Schema(description = "候选人的委托人名字", name = "principalName")
    private String principalName;
}
