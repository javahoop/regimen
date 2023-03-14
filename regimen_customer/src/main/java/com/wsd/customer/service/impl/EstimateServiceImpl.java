package com.wsd.customer.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.customer.dao.CommodityDao;
import com.wsd.customer.dao.CommodityOptionDao;
import com.wsd.customer.dao.EstimateDao;
import com.wsd.customer.model.dto.EstimateSubmitDTO;
import com.wsd.customer.model.dto.GetEstimateDTO;
import com.wsd.customer.model.generate.Estimate;
import com.wsd.customer.model.generate.EstimateExample;
import com.wsd.customer.model.vo.CommodityVO;
import com.wsd.customer.model.vo.EstimateVO;
import com.wsd.customer.service.EstimateService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName EstimateServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 14:05
 * @Version 1.0
 **/
@Service
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private EstimateDao estimateDao;
    @Autowired
    private CommodityOptionDao commodityOptionDao;
    @Override
    public EstimateVO submit(EstimateSubmitDTO param) {
        //获取选项id对应的商品;
        List<CommodityVO> commodityVOS=commodityDao.selectByOptionIds(param.getOptionIds());
        if(CollUtil.isEmpty(commodityVOS)){
            throw ResultCode.COMMODITY_OPTION_FAIL.getBizRunTimeException();
        }
        //获取相应商品关联的所有选项
        BigDecimal fit= BigDecimal.valueOf(0.0);
        Estimate addObject=new Estimate();
        addObject.setCustomerName(param.getCustomerName());
        addObject.setSex(param.getSex());
        addObject.setAge(param.getAge());
        addObject.setCreateTime(new Date());
        addObject.setCreateUser(UserUtils.getUserId());
        addObject.setUpdateTime(new Date());
        addObject.setUpdateUser(UserUtils.getUserId());
        addObject.setIsDelete(BooleanEnum.FALSE.getValue());
        addObject.setIsEnable(BooleanEnum.TURE.getValue());
        for(CommodityVO item: commodityVOS){
            BigDecimal currentFit;
            //获取相应商品关联的所有选项
            List<Long> optionIds= commodityOptionDao.getByCommodityId(item.getId());
            //求当前商品关联选项与用户输入选项的交集个数
            Integer num=getJ(param.getOptionIds(),optionIds);
            //计算与用户输入结果的契合度契合度
            currentFit=BigDecimal.valueOf(( num.doubleValue())/ param.getOptionIds().size());
            //如果契合度为0 进入下一次循环
            if(BigDecimal.ZERO.compareTo(currentFit) == 0){
                continue;
            }
            //比较上一个结果的契合度  如果之前的小 则计算
            if (fit.compareTo(currentFit)<0){
                fit=currentFit;
                addObject.setCommodityId(item.getId());
                addObject.setCommodityName(item.getCommodityName());
                addObject.setCommodityIntroduce(item.getCommodityIntroduce());
            }
        }
        //添加记录
        estimateDao.insert(addObject);
        return this.transform(addObject);
    }

    private EstimateVO transform(Estimate param){
        EstimateVO result=new EstimateVO();
        result.setId(param.getId());
        result.setCustomerName(param.getCustomerName());
        result.setSex(param.getSex());
        result.setAge(param.getAge());
        result.setCommodityId(param.getCommodityId());
        result.setCommodityName(param.getCommodityName());
        result.setCommodityIntroduce(param.getCommodityIntroduce());
        return  result;

    }
    private static Integer getJ(List<Long> m, List<Long> n)
    {
        List<Long> rs = new ArrayList<Long>();

        // 将较长的数组转换为set
        Set<Long> set = new HashSet<Long>(m.size() > n.size() ? m : n);

        // 遍历较短的数组，实现最少循环
        for (Long  i : m.size() > n.size() ? n : m)
        {
            if (set.contains(i))
            {
                rs.add(i);
            }
        }
        return rs.size();
    }

    @Override
    public List<EstimateVO> select(GetEstimateDTO param) {
        EstimateExample.Criteria example=new EstimateExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue()).andIsEnableEqualTo(BooleanEnum.TURE.getValue());
        example.andCreateUserEqualTo(UserUtils.getUserId());

        List<Estimate> estimates=estimateDao.selectByExampleWithBLOBs(example.example());

        return this.transform(estimates);
    }

    private List<EstimateVO> transform(List<Estimate> params){
        List<EstimateVO> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(params)) {
            for (Estimate item : params) {
                EstimateVO obj = new EstimateVO();
                obj.setId(item.getId());
                obj.setCustomerName(item.getCustomerName());
                obj.setSex(item.getSex());
                obj.setAge(item.getAge());
                obj.setCommodityId(item.getCommodityId());
                obj.setCommodityName(item.getCommodityName());
                obj.setCommodityIntroduce(item.getCommodityIntroduce());
                result.add(obj);
            }
        }
        return result;
    }
}
