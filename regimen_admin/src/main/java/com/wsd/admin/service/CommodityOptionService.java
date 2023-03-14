package com.wsd.admin.service;

import com.wsd.admin.model.dto.commodity.option.AddCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.DeletedCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.GetCommodityOptionDTO;
import com.wsd.admin.model.generate.CommodityOption;

import java.util.List;

/**
* @author wu
* @description 针对表【commodity_option(商品与选项关联表)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface CommodityOptionService  {

    void adds(List<AddCommodityOptionDTO> params);

    void deleted(DeletedCommodityOptionDTO param);

    List<CommodityOption> select(GetCommodityOptionDTO param);


}
