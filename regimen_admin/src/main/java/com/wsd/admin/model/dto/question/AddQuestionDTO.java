package com.wsd.admin.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AddProblemDto
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 15:00
 * @Version 1.0
 **/
@Data
@Schema(description = "添加问题入参")
public class AddQuestionDTO {
    /**
     * 题目类型 0:单选 1:多选
     */
    @Schema(description = "题目类型 0:单选 1:多选",required = true, example = "0")
    private Integer subjectType;

    /**
     * 问题内容
     */
    @Schema(description = "问题内容",required = true, example = "你经常熬夜吗？")
    private String subjectContent;

    @Schema(description = "问题选项",required = true, example = "[\n" +
            "      {\n" +
            "          \"optionContent\":\"经常\",\n" +
            "          \"sort\":\"1\"\n" +
            "      },\n" +
            "       {\n" +
            "          \"optionContent\":\"一般\",\n" +
            "          \"sort\":\"2\"\n" +
            "      },\n" +
            "      {\n" +
            "          \"optionContent\":\"不经常\",\n" +
            "          \"sort\":\"3\"\n" +
            "      }\n" +
            "      ]")
    private List<AddOptionDTO> addOptionDTOs;

    @Schema(description = "分类id集合",required = true, example = "[1,2,3]")
    private List<Long> classificationIds;
}
