package com.wsd.admin.dao;

import com.wsd.admin.dao.generate.QuestionMapper;
import com.wsd.admin.model.dto.question.GetQuestionDTO;
import com.wsd.admin.model.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName ProblemDao
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 08:50
 * @Version 1.0
 **/
@Mapper
public interface QuestionDao extends QuestionMapper {
    List<QuestionVO> select(GetQuestionDTO param);
}
