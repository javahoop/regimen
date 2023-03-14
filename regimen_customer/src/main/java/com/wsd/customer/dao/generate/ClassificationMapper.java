package com.wsd.customer.dao.generate;

import com.wsd.customer.model.generate.Classification;
import com.wsd.customer.model.generate.ClassificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassificationMapper {
    long countByExample(ClassificationExample example);

    int deleteByExample(ClassificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Classification record);

    int insertSelective(Classification record);

    Classification selectOneByExample(ClassificationExample example);

    Classification selectOneByExampleSelective(@Param("example") ClassificationExample example, @Param("selective") Classification.Column ... selective);

    List<Classification> selectByExampleSelective(@Param("example") ClassificationExample example, @Param("selective") Classification.Column ... selective);

    List<Classification> selectByExample(ClassificationExample example);

    Classification selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Classification.Column ... selective);

    Classification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Classification record, @Param("example") ClassificationExample example);

    int updateByExample(@Param("record") Classification record, @Param("example") ClassificationExample example);

    int updateByPrimaryKeySelective(Classification record);

    int updateByPrimaryKey(Classification record);

    int batchInsert(@Param("list") List<Classification> list);

    int batchInsertSelective(@Param("list") List<Classification> list, @Param("selective") Classification.Column ... selective);
}