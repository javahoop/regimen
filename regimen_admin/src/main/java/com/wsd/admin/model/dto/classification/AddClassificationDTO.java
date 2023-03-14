package com.wsd.admin.model.dto.classification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddClassificationDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 09:13
 * @Version 1.0
 **/
@Data
@Schema(description = "添加分类入参")
public class AddClassificationDTO {
    /**
     * 分类名称
     */
    @Schema(description = "分类名称",required = true, example = "补气血")
    @NotNull
    private String classificationName;

    /**
     * 顺序
     */
    @Schema(description = "展示顺序",required = true, example = "2")
    @NotNull
    private Integer sort;
}
