package com.wsd.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.wsd.admin.model.dto.commodity.AddCommodityDTO;
import com.wsd.admin.model.dto.commodity.GetCommodityDTO;
import com.wsd.admin.model.dto.commodity.UpdateCommodityDTO;
import com.wsd.admin.model.vo.CommodityVO;
import com.wsd.admin.service.CommodityService;
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
 * @ClassName CommodityController
 * @Description
 * @Author 吴松达
 * @Date 2023/2/28 15:32
 * @Version 1.0
 **/
@RestController
@Tag(name = "商品相关接口")
@RequestMapping("/v1")
@Slf4j
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Operation(summary = "添加商品信息")
    @PostMapping("/commodity")
    public Result add(@RequestBody AddCommodityDTO param){
        commodityService.add(param);

        return Result.success();
    }

    @Operation(summary = "分页查询商品信息")
    @GetMapping("/commodity")
    public Result<ListResult<CommodityVO>> select(@ParameterObject GetCommodityDTO param) {
        Page<Object> page = null;
        if (param.getPage() > 0 && param.getPerPage() > 0) {
            page = PageMethod.startPage(param.getPage(), param.getPerPage());
        }
        List<CommodityVO> records = commodityService.select(param);
        long total = page != null ? page.getTotal() : records.size();
        return Result.success(new ListResult<>(total,records));
    }
    @Operation(summary = "更新商品信息")
    @PostMapping("/commodity/update")
    public Result update(@RequestBody UpdateCommodityDTO param){
         commodityService.update(param);
        return Result.success();
    }
    @Operation(summary = "删除商品信息")
    @DeleteMapping("/commodity/{id}")
    public Result deleted(@PathVariable("id")@Parameter(description = "商品id",required = true) Long id){
        commodityService.deleted(id);
        return Result.success();
    }
}
