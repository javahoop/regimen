package com.wsd.admin.model.dto.classification.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName DelatedClassificationQuestionDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 11:34
 * @Version 1.0
 **/
@Data
public class DeletedClassificationQuestionDTO {
    /**
     * 分类id
     */
    @Schema(description = "分类id", example = "1")
    private Long classificationId;

    /**
     * 题目id
     */
    @Schema(description = "题目id", example = "1")
    private Long questionId;
}
