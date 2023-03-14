package com.wsd.infrastructure.controller.resolvor.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.wsd.infrastructure.em.AbstractEnum;
import com.wsd.infrastructure.resolver.JsonRequestBody;
import com.wsd.infrastructure.utils.CommonUtil;
import com.wsd.infrastructure.utils.EnumUtil;
import com.wsd.infrastructure.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author wsd
 * @date 2021年12月13日 10:56
 **/
@Component
public class DefaultArgHandler extends AbstractArgHandler {
    private static final char ZERO = '0';
    private static final char NINE = '9';
    private static Map<String, Class<?>> basicType = new HashMap<>(16);

    static {
        // 基本类型的处理
        basicType.put("boolean", Boolean.class);
        basicType.put("byte", Byte.class);
        basicType.put("char", Character.class);
        basicType.put("short", Short.class);
        basicType.put("int", Integer.class);
        basicType.put("long", Long.class);
        basicType.put("float", Float.class);
        basicType.put("double", Double.class);

        basicType.put("Boolean", Boolean.class);
        basicType.put("Character", Character.class);
        basicType.put("Byte", Byte.class);
        basicType.put("Short", Short.class);
        basicType.put("Integer", Integer.class);
        basicType.put("Long", Long.class);
        basicType.put("Float", Float.class);
        basicType.put("Double", Double.class);
    }

    public DefaultArgHandler() {
        super(999);
    }

    /**
     * 验证值匹配,如果匹配返回true,如果返回false或者null视为不匹配,只有返回匹配后才会执行getValue
     *
     * @param paramType 参数的类型
     * @return 返回是否匹配
     */
    @Override
    public Boolean match(Class<?> paramType) {
        return Boolean.TRUE;
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
        Class<?> paramType = parameter.getParameterType();
        String paramName = parameter.getParameterName();
        JsonNode node = jsonTree.get(paramName);
        // 简单类型的处理
        if (!isNull(node) && !node.isValueNode()) {
            throw new IllegalArgumentException(paramName + "的值不能是复杂类型");
        }
        if (count == 1 && isNull(node)) {
            if (jsonTree.isValueNode()) {
                // 处理直接传值，没传参数名的情况
                node = jsonTree;
            } else if (jsonTree.size() == 1) {
                // 修正只有一个参数，且也只传了一个参数，但是参数名不对应的问题
                node = jsonTree.fields().next().getValue();
            }
        }
        if (String.class.isAssignableFrom(paramType)) {
            return isNull(node) ? null : node.asText();
        } else if (basicType.get(paramType.getSimpleName()) != null) {
            // 处理基本类型
            return getBasicValue(basicType.get(paramType.getSimpleName()), parameter, node);
        } else if (paramType.isEnum()) {
            // 枚举的处理
            return getEnum(node, paramType);
        } else if (Date.class.isAssignableFrom(paramType)) {
            // 日期转换
            return isNull(node) ? null : JsonUtil.get().convertValue(node.asText(), Date.class);
        } else {
            throw new IllegalArgumentException("不支持[" + paramType.getName() + "]类型的参数");
        }
    }

    /**
     * 获取基本属性值
     *
     * @param type 基本类的包装类型
     * @param parameter 参数类型
     * @param node json化后的对象
     * @return 属性值
     */
    private Object getBasicValue(Class<?> type, MethodParameter parameter, JsonNode node) {

        // 是基本类型或者基本类型的包装类
        if (type.equals(Boolean.class)) {
            return getValueWithDefault((isNull(node) ? null : node.asBoolean()), parameter, v -> false);
        } else if (type.equals(Integer.class)) {
            return getValueWithDefault((isNull(node) ? null : node.asInt()), parameter, v -> 0);
        } else if (type.equals(Long.class)) {
            return getValueWithDefault((isNull(node) ? null : node.asLong()), parameter, v -> 0L);
        } else if (type.equals(Float.class)) {
            Float value = isNull(node) ? null : node.isFloat() ? node.floatValue() : Float.valueOf(node.asText());
            return getValueWithDefault(value, parameter, v -> 0.0f);
        } else if (type.equals(Double.class)) {
            return getValueWithDefault((isNull(node) ? null : node.asDouble()), parameter, v -> 0.0);
        } else {
            throw new RuntimeException("不支持[" + type.getName() + "]类型");
        }
    }

    /**
     * 默认值的获取或者赋值
     *
     * @param value 原值
     * @param parameter 参数类型
     * @param defaultFun 获取默认值的方法
     * @param <T> 基本类型
     * @return
     */
    private <T> Object getValueWithDefault(T value, MethodParameter parameter, Function<T, T> defaultFun) {
        if (parameter.getParameterType().isPrimitive()) {
            JsonRequestBody requestAn = parameter.getMethod().getAnnotation(JsonRequestBody.class) == null
                    ? parameter.getMethod().getDeclaringClass().getAnnotation(JsonRequestBody.class)
                    : parameter.getMethod().getAnnotation(JsonRequestBody.class);

            if (value == null && !requestAn.withDefault()) {
                throw new IllegalArgumentException(parameter.getParameterName() + "是必须的参数");
            }
            return value == null ? defaultFun : value;
        }
        return value;
    }

    /**
     * 获取枚举值
     *
     * @param node
     * @param paramType
     * @return
     */
    private Object getEnum(JsonNode node, Class paramType) {
        if (isNull(node)) {
            return null;
        }
        char first = node.asText().charAt(0);
        boolean firstValidated = first >= ZERO && first <= NINE;
        if (node.isNumber() || firstValidated) {
            Object value = null;
            if (AbstractEnum.class.isAssignableFrom(paramType)) {
                value = AbstractEnum.findByValue(node.asInt(), paramType);
            } else {
                int i = node.asInt();
                for (Enum e : ((Class<Enum>) paramType).getEnumConstants()) {
                    if (e.ordinal() == i) {
                        value = e;
                        break;
                    }
                }
            }
            return value;
        } else {
            return CommonUtil.isBlank(node.asText()) ? null : EnumUtil.codeOf(paramType, node.asText());
        }
    }
}
