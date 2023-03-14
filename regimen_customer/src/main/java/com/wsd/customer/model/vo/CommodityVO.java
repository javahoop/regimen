package com.wsd.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName CommodityVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 09:19
 * @Version 1.0
 **/
@Data
@Schema(description = "查询商品响应结果")
public class CommodityVO {
    /**
     * 商品id
     */
    @Schema(description = "商品id", example = "1")
    private Long id;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称", example = "1")
    private String commodityName;

    /**
     * 商品介绍
     */
    @Schema(description = "商品介绍", example = "1")
    private String commodityIntroduce;
}
