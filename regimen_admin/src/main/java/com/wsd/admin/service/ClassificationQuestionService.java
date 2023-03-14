package com.wsd.admin.service;

import com.wsd.admin.model.dto.classification.question.AddClassificationQuestionDTO;
import com.wsd.admin.model.dto.classification.question.DeletedClassificationQuestionDTO;
import com.wsd.admin.model.vo.ClassificationVO;

import java.util.List;

/**
 * @ClassName ClassificationQuestionService
 * @Description 分类 题目接口
 * @Author 吴松达
 * @Date 2023/3/3 10:44
 * @Version 1.0
 **/
public interface ClassificationQuestionService {
    void adds(List<AddClassificationQuestionDTO> params);

    void deleted(DeletedClassificationQuestionDTO param);

    List<ClassificationVO> select(Long questionId);
}
