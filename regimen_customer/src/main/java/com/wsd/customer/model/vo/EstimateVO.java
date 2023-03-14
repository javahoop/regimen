package com.wsd.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName EstimateVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 10:58
 * @Version 1.0
 **/
@Data
@Schema(description = "评估记录信息")
public class EstimateVO {

    @Schema(description = "评估记录id", example = "1")
    private Long id;
    /**
     * 用户名称
     */
    @Schema(description = "用户名称", example = "12")
    private String customerName;

    /**
     * 性别
     */
    @Schema(description = "性别", example = "1")
    private Integer sex;

    /**
     * 年龄
     */
    @Schema(description = "年龄", example = "1")
    private Integer age;
    /**
     * 商品id
     */
    @Schema(description = "商品id", example = "1")
    private Long commodityId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称", example = "测试1")
    private String commodityName;
    /**
     * 商品介绍
     */
    @Schema(description = "商品介绍", example = "好好好")
    private String commodityIntroduce;
}
