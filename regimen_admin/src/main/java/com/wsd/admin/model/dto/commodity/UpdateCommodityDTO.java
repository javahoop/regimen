package com.wsd.admin.model.dto.commodity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UpdateCommodityDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 09:17
 * @Version 1.0
 **/
@Data
public class UpdateCommodityDTO {
    @NotNull
    @Schema(description = "商品id",required = true, example = "3")
    private Long id;
    /**
     * 商品名称
     */
    @Schema(description = "商品名称",required = true, example = "冰糖雪梨")
    private String commodityName;
    /**
     * 商品介绍
     */
    @Schema(description = "商品介绍",required = true, example = "清热去火")
    private String commodityIntroduce;
    @Schema(description = "商品关联选项集合",required = true, example = "[1,2,3]")
    private List<Long> optionIds;
}
