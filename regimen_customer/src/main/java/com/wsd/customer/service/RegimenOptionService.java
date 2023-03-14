package com.wsd.customer.service;


import com.wsd.customer.model.vo.OptionVO;

import java.util.List;

/**
* @author wu
* @description 针对表【option(选项)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface RegimenOptionService {



    /**
     * 题目id
     * @param questionId
     * @return
     */
    List<OptionVO> select(Long questionId);
}
