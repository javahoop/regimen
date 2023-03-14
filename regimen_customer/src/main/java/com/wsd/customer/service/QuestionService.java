package com.wsd.customer.service;


import com.wsd.customer.model.dto.GetQuestionDTO;
import com.wsd.customer.model.vo.QuestionVO;

import java.util.List;

/**
* @author wu
* @description 针对表【problem(题目)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface QuestionService {


    List<QuestionVO> select(GetQuestionDTO param);
}
