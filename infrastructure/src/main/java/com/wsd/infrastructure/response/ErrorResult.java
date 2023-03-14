package com.wsd.infrastructure.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wsd.infrastructure.constants.HttpErrorEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * API错误响应结果封装类
 * @author wsd
 * @date 2021/1/1
 */
@Getter
@Schema(description = "API错误响应结果")
public class ErrorResult {
    /**
     * 状态码
     */
    @Schema(description = "错误码", example = "503")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String code;
    /**
     * 响应消息
     */
    @Schema(description = "错误信息", example = "未知错误")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;

    /**
     * 构造响应结果
     * @param code 状态码
     * @param message 响应消息
     */
    private ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回错误数据(默认响应)
     */
    public static ErrorResult error() {
        return new ErrorResult(String.valueOf(HttpErrorEnum.UNABLE_TO_PROCESS.getCode()), HttpErrorEnum.UNABLE_TO_PROCESS.getMessage());
    }

    /**
     * 返回错误信息(包含默认http错误状态和自定义错误信息)
     */
    public static ErrorResult error(String message) {
        return new ErrorResult(String.valueOf(HttpErrorEnum.UNABLE_TO_PROCESS.getCode()), message);
    }

    /**
     * 返回错误信息(包含自定义错误码和错误信息)
     */
    public static ErrorResult error(String code, String message) {
        return new ErrorResult(code, message);
    }
}
