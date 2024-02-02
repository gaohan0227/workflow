package org.bigant.wf.instances.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * 发起审批实例实体
 *
 * @author galen
 * @date 2024/1/3114:54
 */
@Data
@ApiModel("发起审批实例")
public class InstancesPreview {
    @ApiModelProperty("审批实例code")
    private String code;

    @ApiModelProperty("发起人id")
    private String userId;

    @ApiModelProperty("发起人id")
    private String deptId;

    @ApiModelProperty("自选节点用户（与 targetSelectUsersAuthMatch 只能二选一）")
    private List<TargetSelectUser> targetSelectUsers;

    @ApiModelProperty("自选节点用户自动匹配 （与 targetSelectUsers 只能二选一）")
    private List<TargetSelectUserAuthMatch> targetSelectUsersAuthMatch;


    @ApiModel("自选节点用户")
    @Data
    public static class TargetSelectUser {
        @ApiModelProperty("节点key")
        private String key;
        @ApiModelProperty("用户id")
        private List<String> userIds;
    }

    @Data
    @ApiModel("自选节点用户自动匹配")
    public static class TargetSelectUserAuthMatch {

        @ApiModelProperty("用户id")
        private List<String> userIds;
    }

}