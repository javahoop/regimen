package com.wsd.admin.model.dto.classification;

import com.wsd.infrastructure.constants.PageParameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName GetClassificationDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 14:13
 * @Version 1.0
 **/
@Data
@Schema(description = "查询分类入参")
public class GetClassificationDTO extends PageParameter {


    /**
     * 分类名称
     */
    @Schema(description = "分类名称",required = true, example = "补气血")
    private String classificationName;

}
