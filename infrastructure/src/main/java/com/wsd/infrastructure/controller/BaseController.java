package com.wsd.infrastructure.controller;


import com.wsd.infrastructure.excption.ResultCode;
import com.wsd.infrastructure.response.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @date 2021-04-01
 * @description 基础controller
 */
@RestController
public abstract class BaseController {

    public ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 返回空结果
     *
     * @return 返回值
     */
    public Result<String> success() {
        return new Result<>();
    }

    public <T> Result<T> success(T data) {
        return Result.success(data);
    }

    public Result error(ResultCode result) {
        return Result.error(result.getCode(), result.getDesc());
    }

    public Result error(String code, String errorMsg) {
        return Result.error(code, errorMsg);
    }

    /*public SessionUser getUser() {
        Object ob = this.getHttpServletRequest().getAttribute(Constants.USER);
        return ob == null ? null : (SessionUser) ob;
    }*/
}
