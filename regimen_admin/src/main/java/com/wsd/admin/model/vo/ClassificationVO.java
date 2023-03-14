package com.wsd.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

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

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "1")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "")
    private Date updateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人id", example = "1")
    private Long createUser;

    /**
     * 更新人
     */
    @Schema(description = "更新人id", example = "1")
    private Long updateUser;

    /**
     * 是否启用 0:不启用 1:启用
     */
    @Schema(description = "是否启用 0:不启用 1:启用", example = "1")
    private Integer isEnable;

    /**
     * 是否删除 0:未删除 1:已删除
     */
    @Schema(description = "是否删除 0:未删除 1:已删除", example = "[1,2,3,4]")
    private Integer isDelete;
}
