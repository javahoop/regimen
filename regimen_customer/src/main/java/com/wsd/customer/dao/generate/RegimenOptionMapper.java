package com.wsd.customer.dao.generate;

import com.wsd.customer.model.generate.RegimenOption;
import com.wsd.customer.model.generate.RegimenOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegimenOptionMapper {
    long countByExample(RegimenOptionExample example);

    int deleteByExample(RegimenOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RegimenOption record);

    int insertSelective(RegimenOption record);

    RegimenOption selectOneByExample(RegimenOptionExample example);

    RegimenOption selectOneByExampleSelective(@Param("example") RegimenOptionExample example, @Param("selective") RegimenOption.Column ... selective);

    List<RegimenOption> selectByExampleSelective(@Param("example") RegimenOptionExample example, @Param("selective") RegimenOption.Column ... selective);

    List<RegimenOption> selectByExample(RegimenOptionExample example);

    RegimenOption selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RegimenOption.Column ... selective);

    RegimenOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RegimenOption record, @Param("example") RegimenOptionExample example);

    int updateByExample(@Param("record") RegimenOption record, @Param("example") RegimenOptionExample example);

    int updateByPrimaryKeySelective(RegimenOption record);

    int updateByPrimaryKey(RegimenOption record);

    int batchInsert(@Param("list") List<RegimenOption> list);

    int batchInsertSelective(@Param("list") List<RegimenOption> list, @Param("selective") RegimenOption.Column ... selective);
}