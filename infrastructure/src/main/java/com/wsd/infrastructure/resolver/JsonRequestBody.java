package com.wsd.infrastructure.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wsd
 * @date 2021-04-08
 * @description 标识一个请求的请求体是json
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonRequestBody {
    /**
     * 是否使用默认值，针对基本类型的时候，如果是允许默认值，则会赋予参数默认值，如果是不允许，则抛出异常
     *
     * @return
     */
    boolean withDefault() default false;

    /**
     * 初始化空对象，如果传的参数不是基本类型或者枚举，且当传的值是空时则会调用类的午餐构造函数初始化一个对象
     *
     * @return
     */
    boolean initObject() default true;
}
