package com.wsd.admin.dao.generate;

import com.wsd.admin.model.generate.ClassificationQuestion;
import com.wsd.admin.model.generate.ClassificationQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassificationQuestionMapper {
    long countByExample(ClassificationQuestionExample example);

    int deleteByExample(ClassificationQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClassificationQuestion record);

    int insertSelective(ClassificationQuestion record);

    ClassificationQuestion selectOneByExample(ClassificationQuestionExample example);

    ClassificationQuestion selectOneByExampleSelective(@Param("example") ClassificationQuestionExample example, @Param("selective") ClassificationQuestion.Column ... selective);

    List<ClassificationQuestion> selectByExampleSelective(@Param("example") ClassificationQuestionExample example, @Param("selective") ClassificationQuestion.Column ... selective);

    List<ClassificationQuestion> selectByExample(ClassificationQuestionExample example);

    ClassificationQuestion selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ClassificationQuestion.Column ... selective);

    ClassificationQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClassificationQuestion record, @Param("example") ClassificationQuestionExample example);

    int updateByExample(@Param("record") ClassificationQuestion record, @Param("example") ClassificationQuestionExample example);

    int updateByPrimaryKeySelective(ClassificationQuestion record);

    int updateByPrimaryKey(ClassificationQuestion record);

    int batchInsert(@Param("list") List<ClassificationQuestion> list);

    int batchInsertSelective(@Param("list") List<ClassificationQuestion> list, @Param("selective") ClassificationQuestion.Column ... selective);
}