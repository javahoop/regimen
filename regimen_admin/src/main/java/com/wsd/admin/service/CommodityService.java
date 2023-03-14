package com.wsd.admin.service;

import com.wsd.admin.model.dto.commodity.AddCommodityDTO;
import com.wsd.admin.model.dto.commodity.GetCommodityDTO;
import com.wsd.admin.model.dto.commodity.UpdateCommodityDTO;
import com.wsd.admin.model.vo.CommodityVO;

import java.util.List;

/**
* @author wu
* @description 针对表【commodity(商品)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface CommodityService {

    void add( AddCommodityDTO param);

    void update(UpdateCommodityDTO param);

    void deleted(Long id);
    List<CommodityVO> select(GetCommodityDTO param);
}
