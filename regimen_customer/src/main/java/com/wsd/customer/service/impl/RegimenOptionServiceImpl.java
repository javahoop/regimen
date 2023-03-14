package com.wsd.customer.service.impl;

import com.wsd.customer.dao.RegimenOptionDao;
import com.wsd.customer.model.generate.RegimenOption;
import com.wsd.customer.model.generate.RegimenOptionExample;
import com.wsd.customer.model.vo.OptionVO;
import com.wsd.customer.service.RegimenOptionService;
import com.wsd.infrastructure.em.BooleanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
