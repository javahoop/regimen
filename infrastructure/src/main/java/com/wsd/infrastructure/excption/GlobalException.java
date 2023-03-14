package com.wsd.infrastructure.excption;


import com.wsd.infrastructure.constants.Constants;
import com.wsd.infrastructure.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author wsd
 * @date 2021-03-15
 * @description 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    public static final String DEFAULT_ERROR_CODE = "1000000";

    public static final String DEFAULT_PARAM_ERROR_CODE = "1000001";

    private static final String DEFAULT_ERROR_MSG = "系统升级中,请稍后再试";

    private static final String DEFAULT_404_ERROR_MSG = "资源不存在";

    private static final String DEFAULT_PARAM_ERROR_MSG = "参数不合法";

    /**
     * 环境
     */
    @Value(value = "${spring.profiles.active:dev}")
    private String env;

    private static String getErrorMsg(List<ObjectError> objectErrors) {
        StringBuilder msgBuilder = new StringBuilder();
        for (ObjectError objectError : objectErrors) {
            msgBuilder.append(objectError.getDefaultMessage()).append(",");
        }
        String errorMessage = msgBuilder.toString();
        if (errorMessage.length() > 1) {
            errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
        }
        return errorMessage;
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex 异常信息
     * @return 返回异常结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        if (ex instanceof NoHandlerFoundException) {
            return Result.error(DEFAULT_ERROR_CODE, DEFAULT_404_ERROR_MSG);
        } else {
            if (Constants.DEV_ENV_STR.equals(env)) {
                return Result.error(DEFAULT_ERROR_CODE, ex.getMessage());
            }
            return Result.error(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
        }
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(DEFAULT_PARAM_ERROR_CODE, DEFAULT_PARAM_ERROR_MSG);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Result constraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return Result.error(DEFAULT_PARAM_ERROR_CODE, errorMessage);
        }
        if (Constants.DEV_ENV_STR.equals(env)) {
            return Result.error(DEFAULT_ERROR_CODE, ex.getMessage());
        }
        return new Result(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            return Result.error(DEFAULT_PARAM_ERROR_CODE, getErrorMsg(objectErrors));
        }
        return new Result(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
    }

    @ExceptionHandler({BindException.class})
    public Result bindException(BindException ex) {
        log.error(ex.getMessage(), ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            return Result.error(DEFAULT_PARAM_ERROR_CODE, getErrorMsg(objectErrors));
        }
        return new Result(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
    }
    @ExceptionHandler(value = BizRunTimeException.class)
    public Result bizErrorHandler(BizRunTimeException ex) {
        log.error(ex.getMessage(), ex);
        if (!Objects.equals(ResultCode.VERIFY_USER_FAIL.getCode(), ex.getErrorCode())
                && !Objects.equals(ResultCode.OFF_LIEN.getCode(), ex.getErrorCode())) {}
        return Result.error(ex.getErrorCode(), ex.getErrorMsg());
    }
}
