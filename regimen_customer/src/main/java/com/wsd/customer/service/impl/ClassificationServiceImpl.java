package com.wsd.customer.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.customer.dao.ClassificationDao;
import com.wsd.customer.model.dto.GetClassificationDTO;
import com.wsd.customer.model.generate.Classification;
import com.wsd.customer.model.generate.ClassificationExample;
import com.wsd.customer.model.vo.ClassificationVO;
import com.wsd.customer.service.ClassificationService;
import com.wsd.infrastructure.em.BooleanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ClassificationServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/13 09:11
 * @Version 1.0
 **/
@Service
@Slf4j
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public List<ClassificationVO> selectClassification(GetClassificationDTO param) {
        ClassificationExample.Criteria example=new ClassificationExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        example.example().orderBy("sort");
        List<Classification> result=classificationDao.selectByExample(example.example());
        return this.transform(result);
    }

    private List<ClassificationVO> transform(List<Classification> params){
        List<ClassificationVO> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(params)) {
            for (Classification item : params) {
                ClassificationVO obj = new ClassificationVO();
                obj.setId(item.getId());
                obj.setClassificationName(item.getClassificationName());
                obj.setSort(item.getSort());
                result.add(obj);
            }
        }
        return result;

    }
}
