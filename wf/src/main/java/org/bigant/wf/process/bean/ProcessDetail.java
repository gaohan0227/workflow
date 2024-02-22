package org.bigant.wf.process.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.bigant.wf.form.component.ComponentType;

import java.util.List;

/**
 * 审批流程详情
 *
 * @author galen
 * @date 2024/2/1909:28
 */
@ApiModel("审批流程详情")
@Data
@Builder
public class ProcessDetail {

    private String code;

    private String name;

    private String iconUrl;

    private List<FormItem> form;

    @Data
    @ApiModel("表单字段")
    @Builder
    public static class FormItem {
        @ApiModelProperty("字段id")
        private String id;
        @ApiModelProperty("字段名称")
        private String name;
        @ApiModelProperty("字段类型")
        private ComponentType type;

        @ApiModelProperty("子集")
        private List<FormItem> children;

    }



}