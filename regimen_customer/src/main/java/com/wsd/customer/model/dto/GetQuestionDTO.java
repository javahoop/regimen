package com.wsd.customer.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName GetOptionDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 09:18
 * @Version 1.0
 **/
@Data
@Schema(description = "查询问题入参")
public class GetQuestionDTO {
    @Schema(description = "分类id集合",required = true, example = "[1,2,3]",maxLength = 3,minLength = 1)
    private List<Long> classificationIds;
}
