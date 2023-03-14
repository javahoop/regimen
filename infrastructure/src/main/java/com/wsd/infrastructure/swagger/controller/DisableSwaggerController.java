package com.wsd.infrastructure.swagger.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsd.infrastructure.constants.HttpErrorEnum;
import com.wsd.infrastructure.response.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在生产环境强制禁用swagger
 */
@RestController
@Profile(value = "prod" )
public class DisableSwaggerController {
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 在生产环境禁用swagger
     */
    @RequestMapping({"/swagger-ui.html", "/swagger-ui/index.html", "/doc.html"})
    public void disableSwagger(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(
                objectMapper.writeValueAsString(
                        ErrorResult.error(String.valueOf(HttpErrorEnum.NOT_FOUND.getCode()),
                                HttpErrorEnum.NOT_FOUND.getMessage())
                )
        );
    }
}
