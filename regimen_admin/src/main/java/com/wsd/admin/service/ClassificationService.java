package com.wsd.admin.service;

import com.wsd.admin.model.dto.classification.AddClassificationDTO;
import com.wsd.admin.model.dto.classification.GetClassificationDTO;
import com.wsd.admin.model.dto.classification.UpdateClassificationDTO;
import com.wsd.admin.model.vo.ClassificationVO;

import java.util.List;

/**
* @author wu
* @description 针对表【classification(分类)】的数据库操作Service
* @createDate 2023-03-01 09:08:07
*/
public interface ClassificationService {
    /**
     * 添加分类
     * @param param
     */
    void addClassification(AddClassificationDTO param);

    List<ClassificationVO> selectClassification(GetClassificationDTO param);

     void update(UpdateClassificationDTO param);

    void deleted(Long id);
}
