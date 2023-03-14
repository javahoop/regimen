package com.wsd.admin.dao;

import com.wsd.admin.dao.generate.ClassificationMapper;
import com.wsd.admin.model.dto.classification.GetClassificationDTO;
import com.wsd.admin.model.vo.ClassificationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName ClassificationDao
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 08:48
 * @Version 1.0
 **/
@Mapper
public interface ClassificationDao extends ClassificationMapper {
    List<ClassificationVO> selectClassification(GetClassificationDTO param);
}
