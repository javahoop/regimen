package com.wsd.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.admin.dao.ClassificationQuestionDao;
import com.wsd.admin.model.dto.classification.question.AddClassificationQuestionDTO;
import com.wsd.admin.model.dto.classification.question.DeletedClassificationQuestionDTO;
import com.wsd.admin.model.generate.ClassificationQuestion;
import com.wsd.admin.model.generate.ClassificationQuestionExample;
import com.wsd.admin.model.vo.ClassificationVO;
import com.wsd.admin.service.ClassificationQuestionService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName ClassificationQuestionServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/3 10:44
 * @Version 1.0
 **/
@Service
@Slf4j
public class ClassificationQuestionServiceImpl implements ClassificationQuestionService {
    @Autowired
    private ClassificationQuestionDao classificationQuestionDao;
    @Override
    public void adds(List<AddClassificationQuestionDTO> params) {
        List<ClassificationQuestion> result=this.transform(params);
        if(result.size()>0) {
            classificationQuestionDao.batchInsert(result);
        }
    }
    private List<ClassificationQuestion> transform(List<AddClassificationQuestionDTO> params){
        List<ClassificationQuestion> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(params)) {
            for (AddClassificationQuestionDTO item : params) {
                ClassificationQuestion classificationQuestion = new ClassificationQuestion();
                classificationQuestion.setClassificationId(item.getClassificationId());
                classificationQuestion.setQuestionId(item.getQuestionId());
                classificationQuestion.setCreateTime(new Date());
                classificationQuestion.setUpdateTime(new Date());
                classificationQuestion.setCreateUser(UserUtils.getUserId());
                classificationQuestion.setUpdateUser(UserUtils.getUserId());
                classificationQuestion.setIsEnable(BooleanEnum.TURE.getValue());
                classificationQuestion.setIsDelete(BooleanEnum.FALSE.getValue());
                result.add(classificationQuestion);
            }
        }
        return  result;

    }

    public void deleted(DeletedClassificationQuestionDTO param){
        ClassificationQuestionExample.Criteria example=new ClassificationQuestionExample().createCriteria().andIsDeleteEqualTo(BooleanEnum.FALSE.getValue());
        if (Objects.nonNull(param.getClassificationId())){
            example.andClassificationIdEqualTo(param.getClassificationId());
        }
        if(Objects.nonNull(param.getQuestionId())){
            example.andQuestionIdEqualTo(param.getQuestionId());
        }
        ClassificationQuestion deletedObject=new ClassificationQuestion();
        deletedObject.setIsDelete(BooleanEnum.TURE.getValue());
        deletedObject.setUpdateTime(new Date());
        deletedObject.setUpdateUser(UserUtils.getUserId());
        classificationQuestionDao.updateByExampleSelective(deletedObject,example.example());
    }

    @Override
    public List<ClassificationVO> select(@NotNull Long questionId) {
        return classificationQuestionDao.getClassificationByQuestionId(questionId);
    }


}
