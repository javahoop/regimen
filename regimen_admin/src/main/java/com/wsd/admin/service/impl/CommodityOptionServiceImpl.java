package com.wsd.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.admin.dao.CommodityOptionDao;
import com.wsd.admin.model.dto.commodity.option.AddCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.DeletedCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.GetCommodityOptionDTO;
import com.wsd.admin.model.generate.CommodityOption;
import com.wsd.admin.model.generate.CommodityOptionExample;
import com.wsd.admin.service.CommodityOptionService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName CommodityOptionServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 10:24
 * @Version 1.0
 **/
@Service
@Slf4j
public class CommodityOptionServiceImpl implements CommodityOptionService {
    @Autowired
    CommodityOptionDao commodityOptionDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void adds(List<AddCommodityOptionDTO> params) {
        if(CollUtil.isNotEmpty(params)) {
            commodityOptionDao.batchInsert(this.transform(params));
        }
    }

    @Override
    public void deleted(DeletedCommodityOptionDTO param) {
        CommodityOptionExample.Criteria example=new CommodityOptionExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        if(Objects.isNull(param.getCommodityId()) && Objects.isNull(param.getOptionId())){
            throw ResultCode.ADD_FAIL.getBizRunTimeException();

        }
        if(Objects.nonNull(param.getCommodityId())){
            example.andCommodityIdEqualTo(param.getCommodityId());
        }
        if(Objects.nonNull(param.getOptionId())){
            example.andOptionIdEqualTo(param.getOptionId());
        }
        CommodityOption updateObject=new CommodityOption();
        updateObject.setUpdateUser(UserUtils.getUserId());
        updateObject.setUpdateTime(new Date());
        updateObject.setIsDelete(BooleanEnum.TURE.getValue());

        commodityOptionDao.updateByExampleSelective(updateObject,example.example());
    }

    @Override
    public List<CommodityOption> select(GetCommodityOptionDTO param){
        CommodityOptionExample.Criteria example=new CommodityOptionExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        if(Objects.nonNull(param.getCommodityId())){
            example.andCommodityIdEqualTo(param.getCommodityId());
        }
        if(Objects.nonNull(param.getOptionId())){
            example.andOptionIdEqualTo(param.getOptionId());
        }
        return commodityOptionDao.selectByExample(example.example());
    }
    private List<CommodityOption> transform(List<AddCommodityOptionDTO> params){
        List<CommodityOption> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(params)) {
            for (AddCommodityOptionDTO item : params) {
                CommodityOption object = new CommodityOption();
                object.setCommodityId(item.getCommodityId());
                object.setOptionId(item.getOptionId());
                object.setCreateTime(new Date());
                object.setUpdateTime(new Date());
                object.setCreateUser(UserUtils.getUserId());
                object.setUpdateUser(UserUtils.getUserId());
                object.setIsEnable(BooleanEnum.TURE.getValue());
                object.setIsDelete(BooleanEnum.FALSE.getValue());
                result.add(object);
            }
        }
        return result;
    }
}
