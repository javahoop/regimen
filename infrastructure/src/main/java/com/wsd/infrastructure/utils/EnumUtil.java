package com.wsd.infrastructure.utils;


import com.wsd.infrastructure.em.AbstractEnum;

/**
 * 枚举反转换工具
 *
 * @author wsd
 * @date 2021/03/18
 * @since V1.0.1
 */
public class EnumUtil {
    /**
     * 把具体值转换成对应枚举
     *
     * @param enumClass 对应的枚举类
     * @param code      枚举值
     * @param <E>       类型
     * @return 具体枚举
     */
    public static <E extends AbstractEnum<?>> E codeOf(Class<E> enumClass, Integer code) {
        E[] enums = enumClass.getEnumConstants();
        for (E e : enums) {
            if (e.getValue().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<?>> E codeOf(Class<E> enumClass, String name) {
        E[] enums = enumClass.getEnumConstants();
        for (E e : enums) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
