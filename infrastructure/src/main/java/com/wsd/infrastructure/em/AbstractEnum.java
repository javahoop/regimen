package com.wsd.infrastructure.em;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wsd.infrastructure.resolver.BaseEnumDeserializer;
import com.wsd.infrastructure.utils.CommonUtil;

import java.util.Arrays;
import java.util.Objects;


/**
 * 枚举基础类
 *
 * @author lisheng
 * @date 2021/03/18
 * @since V1.0.1
 */
@JsonDeserialize(using = BaseEnumDeserializer.class)
public interface AbstractEnum<E extends Enum<E>> {
    /**
     * 枚举转换
     *
     * @param v 枚举值
     * @param clazz 枚举类
     * @param <E> 枚举对象
     * @return 枚举
     * @since 1.0.8
     */
    public static <E extends Enum<E>> E findByName(String v, Class<E> clazz) {
        return Enum.valueOf(clazz, v);
    }

    /**
     * 枚举转换
     *
     * @param v 枚举值
     * @param clazz 枚举类
     * @param <E> 枚举对象
     * @return 枚举
     * @since 1.0.8
     */
    public static <E extends Enum<E> & AbstractEnum, T> E findByValue(T v, Class<E> clazz) {
        E[] es = clazz.getEnumConstants();
        for (E e : es) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(clazz.getName() + ".value=" + v + " is not exist");
    }

    /**
     * 通过描述获取枚举
     *
     * @param desc 描述
     * @param clazz 类型
     * @param <E>
     * @param <T>
     * @return
     */
    static <E extends Enum<E> & AbstractEnum, T> E findByDesc(String desc, Class<E> clazz) {
        if (CommonUtil.isBlank(desc)) {
            return null;
        }
        return Arrays.stream(clazz.getEnumConstants()).filter(e -> Objects.equals(e.getDesc(), desc)).findFirst()
                .orElse(null);
    }

    /**
     * 真正与数据库进行映射的值
     *
     * @return
     */
    Integer getValue();

    /**
     * 显示的信息
     *
     * @return
     */
    String getDesc();

}
