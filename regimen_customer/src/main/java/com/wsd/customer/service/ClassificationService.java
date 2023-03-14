package com.wsd.customer.service;


import com.wsd.customer.model.dto.GetClassificationDTO;
import com.wsd.customer.model.vo.ClassificationVO;

import java.util.List;

/**
* @author wu
* @description 针对表【classification(分类)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface ClassificationService {


    List<ClassificationVO> selectClassification(GetClassificationDTO param);

}
