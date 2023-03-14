package com.wsd.customer.dao.generate;

import com.wsd.customer.model.generate.Question;
import com.wsd.customer.model.generate.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectOneByExample(QuestionExample example);

    Question selectOneByExampleSelective(@Param("example") QuestionExample example, @Param("selective") Question.Column ... selective);

    List<Question> selectByExampleSelective(@Param("example") QuestionExample example, @Param("selective") Question.Column ... selective);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Question.Column ... selective);

    Question selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    int batchInsert(@Param("list") List<Question> list);

    int batchInsertSelective(@Param("list") List<Question> list, @Param("selective") Question.Column ... selective);
}