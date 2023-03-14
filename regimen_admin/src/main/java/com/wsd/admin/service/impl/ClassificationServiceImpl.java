package com.wsd.admin.service.impl;

import com.wsd.admin.dao.ClassificationDao;
import com.wsd.admin.model.dto.classification.AddClassificationDTO;
import com.wsd.admin.model.dto.classification.GetClassificationDTO;
import com.wsd.admin.model.dto.classification.UpdateClassificationDTO;
import com.wsd.admin.model.dto.classification.question.DeletedClassificationQuestionDTO;
import com.wsd.admin.model.generate.Classification;
import com.wsd.admin.model.vo.ClassificationVO;
import com.wsd.admin.service.ClassificationQuestionService;
import com.wsd.admin.service.ClassificationService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ClassificationServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 09:11
 * @Version 1.0
 **/
@Service
@Slf4j
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Autowired
    private ClassificationQuestionService classificationQuestionService;
    @Override
    public void addClassification(AddClassificationDTO param) {
            if(classificationDao.insert(this.transform(param))==0){
                throw ResultCode.ADD_FAIL.getBizRunTimeException();
            }
    }

    @Override
    public List<ClassificationVO> selectClassification(GetClassificationDTO param) {
       List<ClassificationVO> classifications = classificationDao.selectClassification(param);
        return classifications;
    }
    public void update(UpdateClassificationDTO param){
         if(classificationDao.updateByPrimaryKeySelective(this.transform(param))==0){
             throw ResultCode.UPDATE_FAIL.getBizRunTimeException();
         }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(@NotNull Long id) {
        Classification result=new Classification();
        result.setId(id);
        result.setIsDelete(BooleanEnum.TURE.getValue());
        result.setUpdateUser(UserUtils.getUserId());
        //更新分类删除状态
        if(classificationDao.updateByPrimaryKeySelective(result)==0){
            throw ResultCode.DELETED_FAIL.getBizRunTimeException();
        }
        //删除与分类关联问题表中的信息
        DeletedClassificationQuestionDTO deletedClassificationQuestionDTO=new DeletedClassificationQuestionDTO();
        deletedClassificationQuestionDTO.setClassificationId(id);
        classificationQuestionService.deleted(deletedClassificationQuestionDTO);
    }

    private Classification transform(UpdateClassificationDTO param){
        Classification result=new Classification();
        result.setId(param.getId());
        result.setClassificationName(param.getClassificationName());
        result.setSort(param.getSort());
        result.setUpdateTime(new Date());
        result.setUpdateUser(UserUtils.getUserId());
        result.setIsEnable(param.getIsEnable());
        return result;
    }
    private Classification transform(AddClassificationDTO param){
        Classification result=new Classification();
        result.setClassificationName(param.getClassificationName());
        result.setSort(param.getSort());
        result.setCreateTime(new Date());
        result.setUpdateTime(new Date());
        result.setCreateUser(UserUtils.getUserId());
        result.setUpdateUser(UserUtils.getUserId());
        result.setIsEnable(BooleanEnum.TURE.getValue());
        result.setIsDelete(BooleanEnum.FALSE.getValue());
        return result;
    }
}
