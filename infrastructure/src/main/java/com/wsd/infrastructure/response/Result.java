package com.wsd.infrastructure.response;

import com.wsd.infrastructure.excption.BaseErrorCode;

/**
 * @author wsd
 * @date 2021-03-15
 * @description 统一返回结果集
 */
public class Result<T> {
    private String errorCode;
    private String errorMsg;
    private Boolean success;
    private T data;
    private ResultStatus status;


    public Result(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = ResultStatus.ERROR;
        this.success = Boolean.FALSE;
    }

    public Result(T data) {
        this.data = data;
        this.status = ResultStatus.OK;
        this.success = Boolean.TRUE;
    }

    public Result(ResultStatus status) {
        this.status = status;
        this.success = ResultStatus.OK.equals(status);
    }

    public Result() {
        this.status = ResultStatus.OK;
        this.success = Boolean.TRUE;
    }

    public static Result error(String errorCode, String errorMsg) {
        return new Result(errorCode, errorMsg);
    }

    public static Result error(BaseErrorCode errorCode) {
        return new Result(errorCode.getCode(), errorCode.getDesc());
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result<String> success() {
        return new Result<>();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    public ResultStatus getStatus() {
        return status;
    }

    /**
     * 返回状态
     */
    public enum ResultStatus {
        /**
         * 成功
         */
        OK,
        /**
         * 失败
         */
        ERROR
    }
}
