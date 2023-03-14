package com.wsd.admin.service;

import com.wsd.admin.model.dto.question.AddQuestionDTO;
import com.wsd.admin.model.dto.question.GetQuestionDTO;
import com.wsd.admin.model.dto.question.UpdateQuestionDTO;
import com.wsd.admin.model.vo.QuestionVO;

import java.util.List;

/**
* @author wu
* @description 针对表【problem(题目)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface QuestionService {

    void add(AddQuestionDTO param);
    void update(UpdateQuestionDTO param);

    void deleted(Long id);

    List<QuestionVO> select(GetQuestionDTO param);
}
