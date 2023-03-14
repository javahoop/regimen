package com.wsd.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wsd.admin.dao.QuestionDao;
import com.wsd.admin.model.dto.classification.question.AddClassificationQuestionDTO;
import com.wsd.admin.model.dto.classification.question.DeletedClassificationQuestionDTO;
import com.wsd.admin.model.dto.question.AddQuestionDTO;
import com.wsd.admin.model.dto.question.GetQuestionDTO;
import com.wsd.admin.model.dto.question.UpdateQuestionDTO;
import com.wsd.admin.model.generate.Question;
import com.wsd.admin.model.vo.QuestionVO;
import com.wsd.admin.service.ClassificationQuestionService;
import com.wsd.admin.service.RegimenOptionService;
import com.wsd.admin.service.QuestionService;
import com.wsd.infrastructure.em.BooleanEnum;
import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ProblemServiceImpl
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 14:54
 * @Version 1.0
 **/
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private RegimenOptionService regimenOptionService;
    @Autowired
    private ClassificationQuestionService classificationQuestionService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AddQuestionDTO param) {
        Question addObject = this.transform(param);
        if (questionDao.insertSelective(addObject) == 0) {
            throw ResultCode.ADD_FAIL.getBizRunTimeException();
        }
        // 添加问题关联的选项
        if (CollUtil.isNotEmpty(param.getAddOptionDTOs())) {
            for (int i = 0; i < param.getAddOptionDTOs().size(); i++) {
                param.getAddOptionDTOs().get(i).setQuestionId(addObject.getId());
            }
        }
        regimenOptionService.adds(param.getAddOptionDTOs());
        // 添加问题关联的分类
        classificationQuestionService.adds(this.transform(addObject.getId(),param.getClassificationIds()));
    }
    private List<AddClassificationQuestionDTO> transform(Long questionId,List<Long> classificationIds){
        List<AddClassificationQuestionDTO> result=new LinkedList<>();
        if(CollUtil.isNotEmpty(classificationIds)) {
            for (Long item : classificationIds) {
                AddClassificationQuestionDTO i = new AddClassificationQuestionDTO();
                i.setQuestionId(questionId);
                i.setClassificationId(item);
                result.add(i);
            }
        }
        return result;
    }
    private Question transform(AddQuestionDTO param){
        Question result=new Question();
        result.setQuestionType(param.getSubjectType());
        result.setQuestionContent(param.getSubjectContent());
        result.setCreateTime(new Date());
        result.setUpdateTime(new Date());
        result.setCreateUser(UserUtils.getUserId());
        result.setUpdateUser(UserUtils.getUserId());
        result.setIsEnable(BooleanEnum.TURE.getValue());
        result.setIsDelete(BooleanEnum.FALSE.getValue());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UpdateQuestionDTO param) {
        if(questionDao.updateByPrimaryKeySelective(this.transform(param))==0){
            throw ResultCode.UPDATE_FAIL.getBizRunTimeException();
        }
        //删除问题与分类的关联
        DeletedClassificationQuestionDTO deletedClassificationQuestionDTO=new DeletedClassificationQuestionDTO();
        deletedClassificationQuestionDTO.setQuestionId(param.getId());
        classificationQuestionService.deleted(deletedClassificationQuestionDTO);
        //删除问题的选项
        regimenOptionService.deletedByQuestionId(param.getId());
        //将新的问题的选项添加
        if (CollUtil.isNotEmpty(param.getUpdateOptionDTOs())) {
            for (int i = 0; i < param.getUpdateOptionDTOs().size(); i++) {
                param.getUpdateOptionDTOs().get(i).setQuestionId(param.getId());
            }
        }
        regimenOptionService.adds(param.getUpdateOptionDTOs());
        //将新的问题与分类关联添加进去
        classificationQuestionService.adds(this.transform(param.getId(),param.getClassificationIds()));
    }
    private Question transform(UpdateQuestionDTO param){
        Question result=new Question();
        result.setId(param.getId());
        result.setQuestionType(param.getSubjectType());
        result.setQuestionContent(param.getSubjectContent());
        result.setUpdateTime(new Date());
        result.setUpdateUser(UserUtils.getUserId());
        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(@NotNull Long id) {
        Question result=new Question();
        result.setId(id);
        result.setUpdateTime(new Date());
        result.setUpdateUser(UserUtils.getUserId());
        result.setIsDelete(BooleanEnum.TURE.getValue());
        if(questionDao.updateByPrimaryKeySelective(result)==0){
            throw ResultCode.DELETED_FAIL.getBizRunTimeException();
        }
        // 删除题目的选项
          regimenOptionService.deletedByQuestionId(id);
        //删除题目与分类的关联信息
        DeletedClassificationQuestionDTO deletedClassificationQuestionDTO=new DeletedClassificationQuestionDTO();
        deletedClassificationQuestionDTO.setQuestionId(id);
        classificationQuestionService.deleted(deletedClassificationQuestionDTO);
    }

    @Override
    public List<QuestionVO> select(GetQuestionDTO param) {
        List<QuestionVO> result=questionDao.select(param);
        //获取题目对应的选项进行数据整合 返回上一层
        for(QuestionVO item:result){
            item.setOptionVOList(regimenOptionService.select(item.getId()));
            item.setClassificationVOList(classificationQuestionService.select(item.getId()));
        }
        return result;
    }
}
