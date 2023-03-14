package com.wsd.admin.dao.generate;

import com.wsd.admin.model.generate.Commodity;
import com.wsd.admin.model.generate.CommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityMapper {
    long countByExample(CommodityExample example);

    int deleteByExample(CommodityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectOneByExample(CommodityExample example);

    Commodity selectOneByExampleSelective(@Param("example") CommodityExample example, @Param("selective") Commodity.Column ... selective);

    Commodity selectOneByExampleWithBLOBs(CommodityExample example);

    List<Commodity> selectByExampleSelective(@Param("example") CommodityExample example, @Param("selective") Commodity.Column ... selective);

    List<Commodity> selectByExampleWithBLOBs(CommodityExample example);

    List<Commodity> selectByExample(CommodityExample example);

    Commodity selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Commodity.Column ... selective);

    Commodity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByExampleWithBLOBs(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByExample(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKeyWithBLOBs(Commodity record);

    int updateByPrimaryKey(Commodity record);

    int batchInsert(@Param("list") List<Commodity> list);

    int batchInsertSelective(@Param("list") List<Commodity> list, @Param("selective") Commodity.Column ... selective);
}