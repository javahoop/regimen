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

    private Long id;
    /**
     * 用户名称
     */
    private String customerName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品介绍
     */
    private String commodityIntroduce;
}
