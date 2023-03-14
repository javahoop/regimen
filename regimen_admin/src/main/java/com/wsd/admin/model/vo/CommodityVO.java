package com.wsd.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
     * 创建时间
     */
    @Schema(description = "创建时间", example = "1")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "1")
    private Date updateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人", example = "1")
    private Long createUser;

    /**
     * 更新人
     */
    @Schema(description = "更新人", example = "1")
    private Long updateUser;

    /**
     * 是否启用 0:不启用 1:启用
     */
    @Schema(description = "是否启用 0:不启用 1:启用", example = "1")
    private Integer isEnable;

    /**
     * 是否删除 0:未删除 1:已删除
     */
    @Schema(description = "是否删除 0:未删除 1:已删除", example = "0")
    private Integer isDelete;

    /**
     * 商品介绍
     */
    @Schema(description = "商品介绍", example = "1")
    private String commodityIntroduce;

    @Schema(description = "商品关联的选项id", example = "1")
    private List<Long> optionIds;
}
