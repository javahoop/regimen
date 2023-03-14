package com.wsd.admin.model.dto.commodity.option;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName GetCommodityOptionDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/7 14:31
 * @Version 1.0
 **/
@Data
public class GetCommodityOptionDTO {
    /**
     * 商品id
     */
    @Schema(description = "商品id",required = true, example = "1")
    private Long commodityId;

    /**
     * 选项id
     */
    @Schema(description = "选项id",required = true, example = "1")
    private Long optionId;
}
