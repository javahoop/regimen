package com.wsd.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProblemVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 15:16
 * @Version 1.0
 **/
@Data
@Schema(description = "题目响应类")
public class QuestionVO {
    /**
     * 问题id
     */
    @Schema(description = "问题id", example = "1")
    private Long id;

    /**
     * 题目类型 0:单选 1:多选
     */
    @Schema(description = "题目类型 0:单选 1:多选", example = "1")
    private Integer questionType;

    /**
     * 问题内容
     */
    @Schema(description = "问题内容", example = "1")
    private String questionContent;

    /**
     * 选项列表
     */
    @Schema(description = "选项列表", example = "[]")
    private List<OptionVO> optionVOList;
}
