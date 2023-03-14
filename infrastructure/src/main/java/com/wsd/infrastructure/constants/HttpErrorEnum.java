package com.wsd.infrastructure.constants;

import lombok.Getter;

/**
 * 公共API结果响应码定义
 * 状态码不能重复,具体业务继承定义各自的错误码
 * @author wsd
 * @date 2021/5/25
 */
@Getter
public enum HttpErrorEnum {
    /**
     * 通用类响应
     */
    // 请求参数错误,可用于缺少参数,参数不合法等场景
    REQUEST_PARAM_ERROR(400, "接口请求参数错误"),
    // 无效token,可用于用户未登录,登录失效等场景
    INVALID_TOKEN(401, "当前用户令牌无效"),
    // 当前请求无权限访问,获取当前资源无权访问
    INVALID_PERMISSION(403, "没有权限访问当前请求"),
    // 未找到请求,或者未找到资源
    NOT_FOUND(404, "未找到请求"),
    // 当前尝试添加或更改的资源已经存在,或者存在资源冲突
    ALREADY_EXISTS(409, "该数据已经存在"),
    // json请求参数解析错误
    JSON_PARAM_PARSE_ERROR(412, "JSON请求参数解析错误,请确认参数是否符合JSON规范"),
    // 上传文件过大
    TOO_LARGE(413, "上传文件过大"),
    // 提交的实体或参数不被支持
    UNSUPPORTED_PARAM(415, "当前提交的数据或字段不支持"),
    // 操作太频繁
    TOO_MANY_REQUESTS(429, "操作过于频繁,请稍后再试"),
    // 无法处理当前请求
    UNABLE_TO_PROCESS(500, "无法处理当前请求"),
    // 未知错误
    UNKNOWN_ERROR(503, "未知错误");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 响应消息
     */
    private final String message;

    /**
     * 定义响应信息
     *
     * @param code 状态码
     * @param message 错误信息
     */
    HttpErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
