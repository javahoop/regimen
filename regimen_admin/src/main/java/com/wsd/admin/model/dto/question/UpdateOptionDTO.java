package com.wsd.admin.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName AddOptionDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 15:03
 * @Version 1.0
 **/
@Data
@Schema(description = "添加选项入参")
public class UpdateOptionDTO {
    /**
     * 选项内容
     */
    @Schema(description = "选项内容",required = true, example = "经常")
    private String optionContent;

    /**
     * 顺序
     */
    @Schema(description = "选项顺序",required = true, example = "1")
    private Integer sort;
}
