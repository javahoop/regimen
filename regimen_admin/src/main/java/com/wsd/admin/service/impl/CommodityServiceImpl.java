package com.wsd.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.wsd.admin.dao.CommodityDao;
import com.wsd.admin.model.dto.commodity.AddCommodityDTO;
import com.wsd.admin.model.dto.commodity.GetCommodityDTO;
import com.wsd.admin.model.dto.commodity.UpdateCommodityDTO;
import com.wsd.admin.model.dto.commodity.option.AddCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.DeletedCommodityOptionDTO;
import com.wsd.admin.model.dto.commodity.option.GetCommodityOptionDTO;
import com.wsd.admin.model.generate.Commodity;
import com.wsd.admin.model.generate.CommodityExample;
import com.wsd.admin.model.generate.CommodityOption;
import com.wsd.admin.model.vo.CommodityVO;
import com.wsd.admin.service.CommodityOptionService;
import com.wsd.admin.service.CommodityService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CommodityServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 09:23
 * @Version 1.0
 **/
@Service
@Slf4j
public class CommodityServiceImpl  implements CommodityService  {
    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private CommodityOptionService commodityOptionService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AddCommodityDTO param) {
            //添加商品
           Commodity  addObject=this.transform(param);
           if(commodityDao.insertSelective(addObject)==0){
               throw ResultCode.INVALIDATE_PARAM.getBizRunTimeException();
           }
           //批量添加商品与选项的关系
        if(CollUtil.isNotEmpty(param.getOptionIds())) {
            commodityOptionService.adds(this.transform(addObject.getId(),param.getOptionIds()));
        }
    }
    private List<AddCommodityOptionDTO> transform(Long commodityId,List<Long> optionIds){
        List<AddCommodityOptionDTO> result=new LinkedList<>();
        for (Long item:optionIds){
            result.add(new AddCommodityOptionDTO(commodityId,item));
        }
        return  result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UpdateCommodityDTO param) {
        //更新商品信息
        if( commodityDao.updateByPrimaryKeySelective(this.transform(param))==0){
            throw ResultCode.UPDATE_FAIL.getBizRunTimeException();
        }
        //删除商品与选项关联
        DeletedCommodityOptionDTO deletedCommodityOptionDTO=new DeletedCommodityOptionDTO();
        deletedCommodityOptionDTO.setCommodityId(param.getId());
        commodityOptionService.deleted(deletedCommodityOptionDTO);
        //添加商品与选项关联
        if(CollUtil.isNotEmpty(param.getOptionIds())) {
            commodityOptionService.adds(this.transform(param.getId(),param.getOptionIds()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(@NotNull Long id) {
        Commodity deletedObject=new Commodity();
        deletedObject.setId(id);
        deletedObject.setIsDelete(BooleanEnum.TURE.getValue());
        deletedObject.setUpdateTime(new Date());
        deletedObject.setUpdateUser(UserUtils.getUserId());
        if(commodityDao.updateByPrimaryKeySelective(deletedObject)==0){
            throw ResultCode.DELETED_FAIL.getBizRunTimeException();
        }
        //删除商品与选项的关联
        DeletedCommodityOptionDTO param=new DeletedCommodityOptionDTO();
        param.setCommodityId(id);
        commodityOptionService.deleted(param);
    }

    private Commodity transform(AddCommodityDTO param){
        Commodity result=new Commodity();
        result.setCommodityName(param.getCommodityName());
        result.setCreateTime(new Date());
        result.setUpdateTime(new Date());
        result.setCreateUser(UserUtils.getUserId());
        result.setUpdateUser(UserUtils.getUserId());
        result.setCommodityIntroduce(param.getCommodityIntroduce());
        return result;
    }
    private Commodity transform(UpdateCommodityDTO param){
        Commodity result=new Commodity();
        result.setId(param.getId());
        result.setCommodityName(param.getCommodityName());
        result.setUpdateTime(new Date());
        result.setUpdateUser(UserUtils.getUserId());
        result.setCommodityIntroduce(param.getCommodityIntroduce());
        return result;
    }

    @Override
    public List<CommodityVO> select(GetCommodityDTO param) {
        CommodityExample.Criteria example=new CommodityExample().createCriteria();
        example.andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        if(CharSequenceUtil.isNotBlank(param.getCommodityName())){
           example.andCommodityNameLike("%" + param.getCommodityName() + "%");
        }
        List<Commodity> commodities=commodityDao.selectByExampleWithBLOBs(example.example());
        List<CommodityVO> result=new LinkedList<>();
        for (Commodity item:commodities){
            GetCommodityOptionDTO getCommodityOptionDTO=new GetCommodityOptionDTO();
            getCommodityOptionDTO.setCommodityId(item.getId());
            List<CommodityOption> commodityOptions=commodityOptionService.select(getCommodityOptionDTO);
            CommodityVO c=new CommodityVO();
            BeanUtils.copyProperties(item, c);
            List<Long> optionIds=new LinkedList<>();
            for(CommodityOption co:commodityOptions){
                optionIds.add(co.getOptionId());
            }
            c.setOptionIds(optionIds);
            result.add(c);
        }
        return result;
    }
}
