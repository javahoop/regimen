package com.wsd.customer.dao.generate;

import com.wsd.customer.model.generate.CommodityOption;
import com.wsd.customer.model.generate.CommodityOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityOptionMapper {
    long countByExample(CommodityOptionExample example);

    int deleteByExample(CommodityOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityOption record);

    int insertSelective(CommodityOption record);

    CommodityOption selectOneByExample(CommodityOptionExample example);

    CommodityOption selectOneByExampleSelective(@Param("example") CommodityOptionExample example, @Param("selective") CommodityOption.Column ... selective);

    List<CommodityOption> selectByExampleSelective(@Param("example") CommodityOptionExample example, @Param("selective") CommodityOption.Column ... selective);

    List<CommodityOption> selectByExample(CommodityOptionExample example);

    CommodityOption selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") CommodityOption.Column ... selective);

    CommodityOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommodityOption record, @Param("example") CommodityOptionExample example);

    int updateByExample(@Param("record") CommodityOption record, @Param("example") CommodityOptionExample example);

    int updateByPrimaryKeySelective(CommodityOption record);

    int updateByPrimaryKey(CommodityOption record);

    int batchInsert(@Param("list") List<CommodityOption> list);

    int batchInsertSelective(@Param("list") List<CommodityOption> list, @Param("selective") CommodityOption.Column ... selective);
}