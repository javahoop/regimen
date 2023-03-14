package com.wsd.customer.dao.generate;

import com.wsd.customer.model.generate.Estimate;
import com.wsd.customer.model.generate.EstimateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EstimateMapper {
    long countByExample(EstimateExample example);

    int deleteByExample(EstimateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Estimate record);

    int insertSelective(Estimate record);

    Estimate selectOneByExample(EstimateExample example);

    Estimate selectOneByExampleSelective(@Param("example") EstimateExample example, @Param("selective") Estimate.Column ... selective);

    Estimate selectOneByExampleWithBLOBs(EstimateExample example);

    List<Estimate> selectByExampleSelective(@Param("example") EstimateExample example, @Param("selective") Estimate.Column ... selective);

    List<Estimate> selectByExampleWithBLOBs(EstimateExample example);

    List<Estimate> selectByExample(EstimateExample example);

    Estimate selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Estimate.Column ... selective);

    Estimate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Estimate record, @Param("example") EstimateExample example);

    int updateByExampleWithBLOBs(@Param("record") Estimate record, @Param("example") EstimateExample example);

    int updateByExample(@Param("record") Estimate record, @Param("example") EstimateExample example);

    int updateByPrimaryKeySelective(Estimate record);

    int updateByPrimaryKeyWithBLOBs(Estimate record);

    int updateByPrimaryKey(Estimate record);

    int batchInsert(@Param("list") List<Estimate> list);

    int batchInsertSelective(@Param("list") List<Estimate> list, @Param("selective") Estimate.Column ... selective);
}