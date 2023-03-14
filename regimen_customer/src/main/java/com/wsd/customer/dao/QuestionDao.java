package com.wsd.customer.dao;

import com.wsd.customer.dao.generate.QuestionMapper;
import com.wsd.customer.model.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
   List<QuestionVO> selectByClassificationIds(@Param("params") List<Long> params);
}
