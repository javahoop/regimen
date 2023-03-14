package com.wsd.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.wsd.admin.model.dto.question.AddQuestionDTO;
import com.wsd.admin.model.dto.question.GetQuestionDTO;
import com.wsd.admin.model.dto.question.UpdateQuestionDTO;
import com.wsd.admin.model.vo.QuestionVO;
import com.wsd.admin.service.QuestionService;
import com.wsd.infrastructure.response.ListResult;
import com.wsd.infrastructure.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ProblemController
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 15:07
 * @Version 1.0
 **/
@RestController
@Tag(name = "问题相关接口")
@RequestMapping("/v1")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Operation(summary = "添加问题")
    @PostMapping("/subject")
    public Result add(@RequestBody AddQuestionDTO param) {
        questionService.add(param);
        return new Result();
    }
    @Operation(summary = "更新问题")
    @PostMapping("/subject/update")
    public Result update(@RequestBody UpdateQuestionDTO param) {
        questionService.update(param);
        return new Result();
    }
    @Operation(summary = "删除问题")
    @DeleteMapping("/subject/{id}")
    public Result deleted(@PathVariable("id")@Parameter(description = "问题id",required = true) Long id) {
        questionService.deleted(id);
        return new Result();
    }
    @Operation(summary = "查看问题信息列表")
    @GetMapping("/subject")
    public Result<ListResult<QuestionVO>> selectClassification(@ParameterObject GetQuestionDTO param) {
        Page<Object> page = null;
        if (param.getPage() > 0 && param.getPerPage() > 0) {
            page = PageMethod.startPage(param.getPage(), param.getPerPage());
        }
        List<QuestionVO> records =   questionService.select(param);
        long total = page != null ? page.getTotal() : records.size();
        return Result.success(new ListResult<>(total,records));
    }
}
