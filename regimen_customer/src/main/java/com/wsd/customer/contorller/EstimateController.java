package com.wsd.customer.contorller;

import com.wsd.customer.model.dto.EstimateSubmitDTO;
import com.wsd.customer.model.dto.GetEstimateDTO;
import com.wsd.customer.model.vo.EstimateVO;
import com.wsd.customer.service.EstimateService;
import com.wsd.infrastructure.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName EstimateController
 * @Description
 * @Author 吴松达
 * @Date 2023/3/13 14:32
 * @Version 1.0
 **/
@RestController
@Tag(name = "推荐相关接口")
@RequestMapping("/v1")
public class EstimateController {
    @Autowired
    private EstimateService estimateService;
    @Operation(summary = "提交答题记录获取推荐结果")
    @PostMapping("/estimate/submit")
    public EstimateVO submit(@RequestBody  EstimateSubmitDTO param){
        return estimateService.submit(param);
    }
    @Operation(summary = "查看问题信息列表")
    @GetMapping("/estimate")
    public Result<List<EstimateVO>> selectClassification(@ParameterObject GetEstimateDTO param) {
        return Result.success(estimateService.select(param));
    }
}
