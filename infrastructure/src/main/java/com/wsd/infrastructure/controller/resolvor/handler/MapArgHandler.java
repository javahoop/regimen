package com.wsd.infrastructure.controller.resolvor.handler;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.wsd.infrastructure.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wsd
 * @date 2021年12月13日 10:56
 **/
@Component
public class MapArgHandler extends AbstractArgHandler {
    /**
     * 为空的时候默认为这个值，避免每次去做json转换
     */
    private static Map<String, JavaType> javaTypeMap = new HashMap<>(32);

    public MapArgHandler() {
        super(2);
    }

    /**
     * 验证值匹配,如果匹配返回true,如果返回false或者null视为不匹配,只有返回匹配后才会执行getValue
     *
     * @param paramType 参数的类型
     * @return 返回是否匹配
     */
    @Override
    public Boolean match(Class<?> paramType) {
        return Map.class.isAssignableFrom(paramType);
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
        if (parameter.getGenericParameterType() instanceof ParameterizedType) {
            Class keyType =
                    (Class) ((ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments()[0];
            Class valueType =
                    (Class) ((ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments()[1];

            String key = String.format("%s.%s.%s", paramType.getName(), keyType.getName(), valueType.getName());
            node = isNull(node) ? jsonTree : node;
            if (javaTypeMap.get(key) == null) {
                JavaType mapType =
                        JsonUtil.get().getTypeFactory().constructMapType((Class<Map>) paramType, keyType, valueType);
                javaTypeMap.put(key, mapType);
            }
            if (!isNull(node)) {
                value = JsonUtil.parse(node.toString(), javaTypeMap.get(key));
            }
            return value;
        } else {
            throw new IllegalStateException(parameter.getGenericParameterType().getTypeName() + "不是一个能处理的类型");
        }
    }
}
