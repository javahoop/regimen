package com.wsd.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName ClassificationVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 14:25
 * @Version 1.0
 **/
@Data
@Schema(description = "查询分类信息类")
public class ClassificationVO {
    /**
     * 分类id
     */
    @Schema(description = "分类id", example = "1")
    private Long id;

    /**
     * 分类名称
     */
    @Schema(description = "分类名称", example = "1")
    private String classificationName;

    /**
     * 顺序
     */
    @Schema(description = "顺序", example = "1")
    private Integer sort;
}
