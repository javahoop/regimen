package com.wsd.customer.contorller;

import com.wsd.customer.model.dto.GetClassificationDTO;
import com.wsd.customer.model.vo.ClassificationVO;
import com.wsd.customer.service.ClassificationService;
import com.wsd.infrastructure.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ClassificationController
 * @Description
 * @Author 吴松达
 * @Date 2023/3/1 09:18
 * @Version 1.0
 **/
@RestController
@Tag(name = "分类相关接口")
@RequestMapping("/v1")
public class ClassificationController {
    @Autowired
    ClassificationService classificationService;
    @Operation(summary = "查看分类信息列表")
    @GetMapping("/classification")
    public Result<List<ClassificationVO>> selectClassification(@ParameterObject GetClassificationDTO param) {
        List<ClassificationVO> records =   classificationService.selectClassification(param);
        return Result.success(records);
    }
}
