package com.wsd.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName OptionVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 11:41
 * @Version 1.0
 **/
@Data
@Schema(description = "选项响应类")
public class OptionVO {
    /**
     * 选项id
     */
    @Schema(description = "选项id", example = "1")
    private Long id;

    /**
     * 选项内容
     */
    @Schema(description = "选项内容", example = "1")
    private String optionContent;

    /**
     * 题目id
     */
    @Schema(description = "题目id", example = "1")
    private Long questionId;

    /**
     * 顺序
     */
    @Schema(description = "顺序", example = "1")
    private Integer sort;

}
