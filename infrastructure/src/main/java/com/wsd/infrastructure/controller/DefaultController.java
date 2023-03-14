package com.wsd.infrastructure.controller;


import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wsd
 * @date 2021年12月10日 14:07
 **/
@RestController
@RequestMapping
public class DefaultController extends BaseController {
    /**
     * app描述
     */
    @Value("${app.description:资源不存在}")
    private String appDesc;
    /**
     * app name
     */
    @Value("${spring.application.name:default}")
    private String appName;

    @RequestMapping(value = "/")
    public Result defaultMethod() {
        return Result.error(ResultCode.NON_RESULT.getCode(), String.format("欢迎你的到来,这里是%s(%s)", appDesc, appName));
    }
}
