package com.wsd.infrastructure.constants;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author wsd
 * @date 2021-04-01
 * @description 常量表
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    /**
     * 空字符串
     */
    public static final String EMPTY_STR = "";

    /**
     * 表单请求格式
     */
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    /**
     * Json请求格式
     */
    public static final String APPLICATION_JSON_VALUE = "application/json";

    /**
     * 字符串截取或者拼接
     */
    public static final String SPLIT_STR = ",";

    /**
     * 返回结果状态
     */
    public static final String STATUS = "status";

    /**
     * 返回结果字段
     */
    public static final String DATA = "data";
    /**
     * 返回错误信息
     */
    public static final String ERROR_MSG = "errorMsg";
    /**
     * 返回错误码
     */
    public static final String ERROR_CODE = "errorCode";

    /**
     * 返回错误标识
     */
    public static final String STATUS_ERROR = "ERROR";

    /**
     * 研发环境标识
     */
    public static final String DEV_ENV_STR = "dev";

    /**
     * 处理json格式的数据
     */
    public static final String REQUEST_JSON_VALUE = "handled_json_value";


}
