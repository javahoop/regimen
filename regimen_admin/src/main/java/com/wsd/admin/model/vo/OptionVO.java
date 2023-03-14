package com.wsd.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName OptionVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 11:41
 * @Version 1.0
 **/
@Data
@Schema(description = "选项响应类")
public class OptionVO {
    /**
     * 选项id
     */
    @Schema(description = "选项id", example = "1")
    private Long id;

    /**
     * 选项内容
     */
    @Schema(description = "选项内容", example = "1")
    private String optionContent;

    /**
     * 题目id
     */
    @Schema(description = "题目id", example = "1")
    private Long questionId;

    /**
     * 顺序
     */
    @Schema(description = "顺序", example = "1")
    private Integer sort;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "")
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
}
