package com.wsd.admin.model.dto.classification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateClassificationDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 15:19
 * @Version 1.0
 **/
@Data
@Schema(description = "更新分类入参")
public class UpdateClassificationDTO {

    /**
     * 分类id
     */
    @Schema(description = "分类id", example = "1")
    @NotNull
    private Long id;
    /**
     * 分类名称
     */
    @Schema(description = "分类名称",required = true, example = "补气血")
    private String classificationName;
    /**
     * 顺序
     */
    @Schema(description = "分类名称",required = true, example = "补气血")
    private Integer sort;

    /**
     * 是否启用 0:不启用 1:启用
     */
    @Schema(description = "是否启用 0:不启用 1:启用",required = false, example = "0")
    private Integer isEnable;
}
