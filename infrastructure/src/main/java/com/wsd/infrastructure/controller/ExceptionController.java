package com.wsd.infrastructure.controller;


import com.wsd.infrastructure.constants.Constants;
import com.wsd.infrastructure.excption.GlobalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @date 2021-04-07
 * @description 404类错误处理类
 */
@Controller
public class ExceptionController extends BasicErrorController {
    /**
     * app描述
     */
    @Value("${app.description:资源不存在}")
    private String appDesc;

    public ExceptionController() {
        super(new ErrorAttributes() {
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                return null;
            }

            @Override
            public Throwable getError(WebRequest webRequest) {
                return null;
            }
        }, new ErrorProperties());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, String> errorMsg = new HashMap<>(4);
        errorMsg.put(Constants.STATUS, Constants.STATUS_ERROR);
        errorMsg.put(Constants.DATA, null);
        errorMsg.put(Constants.ERROR_CODE, GlobalException.DEFAULT_ERROR_CODE);
        errorMsg.put(Constants.ERROR_MSG, appDesc);
        return new ResponseEntity(errorMsg, HttpStatus.NOT_FOUND);
    }
}
