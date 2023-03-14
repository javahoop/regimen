package com.wsd.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.wsd.admin.model.dto.classification.AddClassificationDTO;
import com.wsd.admin.model.dto.classification.GetClassificationDTO;
import com.wsd.admin.model.dto.classification.UpdateClassificationDTO;
import com.wsd.admin.model.vo.ClassificationVO;
import com.wsd.admin.service.ClassificationService;
import com.wsd.infrastructure.response.ListResult;
import com.wsd.infrastructure.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ClassificationService classificationService;
    @Operation(summary = "添加分类信息")
    @PostMapping("/classification")
    public Result addClassification(@RequestBody AddClassificationDTO param) {
        classificationService.addClassification(param);
        return new Result();
    }

    @Operation(summary = "查看分类信息列表")
    @GetMapping("/classification")
    public Result<ListResult<ClassificationVO>> selectClassification(@ParameterObject GetClassificationDTO param) {
        Page<Object> page = null;
        if (param.getPage() > 0 && param.getPerPage() > 0) {
            page = PageMethod.startPage(param.getPage(), param.getPerPage());
        }
        List<ClassificationVO> records =   classificationService.selectClassification(param);
        long total = page != null ? page.getTotal() : records.size();
        return Result.success(new ListResult<>(total,records));
    }

    @Operation(summary = "更新分类信息")
    @PostMapping("/classification/update")
    public Result update(@RequestBody UpdateClassificationDTO param) {
        classificationService.update(param);
        return new Result();
    }


    @Operation(summary = "删除分类信息")
    @DeleteMapping("/classification/{id}")
    public Result delete(@PathVariable("id")@Parameter(description = "分类id",required = true) Long id) {
        classificationService.deleted(id);
        return new Result();
    }
}
