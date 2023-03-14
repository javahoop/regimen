package com.wsd.customer.contorller;

import com.wsd.customer.model.dto.GetQuestionDTO;
import com.wsd.customer.model.vo.QuestionVO;
import com.wsd.customer.service.QuestionService;
import com.wsd.infrastructure.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    QuestionService questionService;

    @Operation(summary = "查看问题信息列表")
    @PostMapping("/subject")
    public Result<List<QuestionVO>> selectClassification(@RequestBody GetQuestionDTO param) {
        return Result.success(questionService.select(param));
    }
}
