package com.wsd.infrastructure.excption;

/**
 * @author 吴松达
 * @ClassName BizRunTimeExcption.java
 * @Description 业务中运行时异常
 * @createTime 2022年01月20日 15:44:00
 */
public class BizRunTimeException extends RuntimeException{

    private static final long serialVersionUID = -1477327071538069456L;
    private final String errorCode;
    private final String errorMsg;

    public BizRunTimeException(String errorCode, String errorMsg) {
        super(String.format("错误编码:%s,错误信息:%s", errorCode, errorMsg));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizRunTimeException(String errorMsg) {
        super(String.format("错误编码:100,错误信息:%s", errorMsg));
        this.errorCode = "100";
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
