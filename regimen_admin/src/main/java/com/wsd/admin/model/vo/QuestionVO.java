package com.wsd.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProblemVO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 15:16
 * @Version 1.0
 **/
@Data
@Schema(description = "题目响应类")
public class QuestionVO {
    /**
     * 问题id
     */
    @Schema(description = "问题id", example = "1")
    private Long id;

    /**
     * 题目类型 0:单选 1:多选
     */
    @Schema(description = "题目类型 0:单选 1:多选", example = "1")
    private Integer questionType;

    /**
     * 问题内容
     */
    @Schema(description = "问题内容", example = "1")
    private String questionContent;

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

    /**
     * 选项列表
     */
    @Schema(description = "选项列表", example = "[]")
    private List<OptionVO> optionVOList;

    /**
     *  分类信息
     */
    @Schema(description = "分类信息", example = "[]")
    private List<ClassificationVO> classificationVOList;
}
