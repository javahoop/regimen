package com.wsd.admin.service;

import com.wsd.admin.model.dto.question.AddOptionDTO;
import com.wsd.admin.model.vo.OptionVO;

import java.util.List;

/**
* @author wu
* @description 针对表【option(选项)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface RegimenOptionService {
    void adds(List<AddOptionDTO> params);

    /**
     * 题目id
     * @param questionId
     */
    void deletedByQuestionId(Long questionId);

    /**
     * 题目id
     * @param questionId
     * @return
     */
    List<OptionVO> select(Long questionId);
}
