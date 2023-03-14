package com.wsd.customer.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName EstimateSubmit
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 11:00
 * @Version 1.0
 **/
@Data
@Schema(description = "提交答题")
public class EstimateSubmitDTO {
    /**
     * 用户名称
     */
    @NotNull
    @Schema(description = "客户姓名",required = true, example = "张三")
    private String customerName;
    /**
     * 性别
     */
    @NotNull
    @Schema(description = "性别0:女 1:男",required = true, example = "张三")
    private Integer sex;

    /**
     * 年龄
     */
    @NotNull
    @Schema(description = "年龄",required = true, example = "26")
    private Integer age;
    @NotNull
    @Schema(description = "客户回答的选项id集合",required = true, example = "[1,2,3]",minLength = 1)
    private List<Long> optionIds;
}
