package com.wsd.customer.service.impl;

import com.wsd.customer.dao.QuestionDao;
import com.wsd.customer.model.dto.GetQuestionDTO;
import com.wsd.customer.model.vo.QuestionVO;
import com.wsd.customer.service.QuestionService;
import com.wsd.customer.service.RegimenOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    QuestionDao questionDao;
    @Autowired
    RegimenOptionService regimenOptionService;
    @Override
    public List<QuestionVO> select(GetQuestionDTO param) {
        List<QuestionVO> result=questionDao.selectByClassificationIds(param.getClassificationIds());
        //获取题目对应的选项进行数据整合 返回上一层
        for(QuestionVO item:result){
            item.setOptionVOList(regimenOptionService.select(item.getId()));
        }
        return result;
    }
}
