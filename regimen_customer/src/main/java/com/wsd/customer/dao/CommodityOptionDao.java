package com.wsd.customer.dao;

import com.wsd.customer.dao.generate.CommodityOptionMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName CommodityOptionDao
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 14:25
 * @Version 1.0
 **/
@Mapper
public interface CommodityOptionDao extends CommodityOptionMapper {
    List<Long> getByCommodityId(Long CommodityId);
}
