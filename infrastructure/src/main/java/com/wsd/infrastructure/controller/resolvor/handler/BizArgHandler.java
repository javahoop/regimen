package com.wsd.infrastructure.controller.resolvor.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.wsd.infrastructure.resolver.JsonRequestBody;
import com.wsd.infrastructure.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

/**
 * @author wsd
 * @date 2021年12月13日 10:48
 **/
@Component
public class BizArgHandler extends AbstractArgHandler {
    /**
     * 只支持公司的类
     */
    private static final String PREFIX = "com.code";

    public BizArgHandler() {
        super(0);
    }

    /**
     * 验证值匹配,如果匹配返回true,如果返回false或者null视为不匹配,只有返回匹配后才会执行getValue
     *
     * @param paramType 参数的类型
     * @return 返回是否匹配
     */
    @Override
    public Boolean match(Class<?> paramType) {
        return paramType.getName().startsWith(PREFIX) && !paramType.isEnum();
    }

    /**
     * 获取值
     *
     * @param jsonTree 数据节点
     * @param count 当前参数总数
     * @param parameter 方法参数
     * @return 返回获取的值
     * @throws Exception 业务异常
     */
    @Override
    public Object getValue(JsonNode jsonTree, MethodParameter parameter, Integer count) throws Exception {
        Object value = null;
        Class<?> paramType = parameter.getParameterType();
        String paramName = parameter.getParameterName();
        JsonNode node = jsonTree.get(paramName);
        node = (count == 1 || node == null) ? jsonTree : node;
        value = isNull(node) ? null : JsonUtil.parse(node.toString(), paramType);
        if (value == null) {
            JsonRequestBody requestAn = parameter.getMethod().getAnnotation(JsonRequestBody.class) == null
                    ? parameter.getMethod().getDeclaringClass().getAnnotation(JsonRequestBody.class)
                    : parameter.getMethod().getAnnotation(JsonRequestBody.class);
            if (requestAn.initObject()) {
                value = paramType.newInstance();
            }
        }
        return value;
    }
}
