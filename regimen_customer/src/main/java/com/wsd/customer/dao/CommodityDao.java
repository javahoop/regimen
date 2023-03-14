package com.wsd.customer.dao;

import com.wsd.customer.dao.generate.CommodityMapper;
import com.wsd.customer.model.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CommodityDao
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 14:09
 * @Version 1.0
 **/
@Mapper
public interface CommodityDao extends CommodityMapper {
    List<CommodityVO> selectByOptionIds(@Param("optionIds") List<Long> optionIds);
}
