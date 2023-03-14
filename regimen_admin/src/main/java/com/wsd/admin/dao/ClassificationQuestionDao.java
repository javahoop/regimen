package com.wsd.admin.dao;

import com.wsd.admin.dao.generate.ClassificationQuestionMapper;
import com.wsd.admin.model.vo.ClassificationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName ClassificationQuestionDao
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 10:48
 * @Version 1.0
 **/
@Mapper
public interface ClassificationQuestionDao extends ClassificationQuestionMapper {
    List<ClassificationVO> getClassificationByQuestionId(Long questionId);
}
