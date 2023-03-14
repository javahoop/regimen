package com.wsd.admin.model.dto.question;

import com.wsd.infrastructure.constants.PageParameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName GetOptionDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 09:18
 * @Version 1.0
 **/
@Data
@Schema(description = "查询问题入参")
public class GetQuestionDTO extends PageParameter {
    /**
     * 题目类型 0:单选 1:多选
     */
    @Schema(description = "题目类型 0:单选 1:多选",required = true, example = "0")
    private Integer questionType;

    @Schema(description = "分类id",required = true, example = "1")
    private Long classificationId;
}
