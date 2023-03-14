package com.wsd.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.admin.dao.RegimenOptionDao;
import com.wsd.admin.model.dto.commodity.option.DeletedCommodityOptionDTO;
import com.wsd.admin.model.dto.question.AddOptionDTO;
import com.wsd.admin.model.generate.RegimenOption;
import com.wsd.admin.model.generate.RegimenOptionExample;
import com.wsd.admin.model.vo.OptionVO;
import com.wsd.admin.service.CommodityOptionService;
import com.wsd.admin.service.RegimenOptionService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName OptionServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 10:32
 * @Version 1.0
 **/
@Service
@Slf4j
public class RegimenOptionServiceImpl implements RegimenOptionService {
    @Autowired
    private RegimenOptionDao regimenOptionDao;

    @Autowired
    private CommodityOptionService commodityOptionService;
    @Override
    public void adds(List<AddOptionDTO> params) {
        List<RegimenOption> result=this.transforms(params);
        if(result.size()>0) {
            regimenOptionDao.batchInsert(result);
        }
    }
    private List<RegimenOption> transforms(List<AddOptionDTO> params){
        List<RegimenOption> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(params)) {
            for (AddOptionDTO item : params) {
                RegimenOption option = new RegimenOption();
                option.setOptionContent(item.getOptionContent());
                option.setSort(item.getSort());
                option.setQuestionId(item.getQuestionId());
                option.setCreateTime(new Date());
                option.setUpdateTime(new Date());
                option.setCreateUser(UserUtils.getUserId());
                option.setUpdateUser(UserUtils.getUserId());
                option.setIsEnable(BooleanEnum.TURE.getValue());
                option.setIsDelete(BooleanEnum.FALSE.getValue());
                result.add(option);
            }
        }
        return  result;
    }

    /**
     * 根据题目id删除选项
     * @param questionId
     */
    public void deletedByQuestionId(Long questionId){
        //删除选项
        RegimenOptionExample.Criteria example=new RegimenOptionExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        example.andQuestionIdEqualTo(questionId);
        RegimenOption updateObject=new RegimenOption();
        updateObject.setIsDelete(BooleanEnum.TURE.getValue());
        updateObject.setUpdateTime(new Date());
        updateObject.setUpdateUser(UserUtils.getUserId());
        List<RegimenOption> list=regimenOptionDao.selectByExample(example.example());
        //删除选项
        if(regimenOptionDao.updateByExampleSelective(updateObject,example.example())==0){
            throw ResultCode.DELETED_FAIL.getBizRunTimeException();
        }
        //删除选项与商品的关联
        for(RegimenOption item:list) {
           DeletedCommodityOptionDTO deleted= new DeletedCommodityOptionDTO();
           deleted.setOptionId(item.getId());
            commodityOptionService.deleted(deleted);
        }
    }

    @Override
    public List<OptionVO> select(@NotNull Long questionId) {
        RegimenOptionExample.Criteria example=new RegimenOptionExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        example.andQuestionIdEqualTo(questionId);
        List<RegimenOption> regimenOptions=regimenOptionDao.selectByExample(example.example());
        return this.transform(regimenOptions);
    }
    private List<OptionVO>  transform(List<RegimenOption> param){
        List<OptionVO> result=new LinkedList<>();
        for (RegimenOption item:param){
            OptionVO obj=new OptionVO();
            BeanUtils.copyProperties(item,obj);
            result.add(obj);
        }
        return result;
    }

}
