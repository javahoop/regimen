package com.wsd.infrastructure.em;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吴松达
 * @ClassName SexTypeEnum.java
 * @createTime 2022年01月21日 09:33:00
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SexTypeEnum implements AbstractEnum<SexTypeEnum>{
    /**
     * 性别枚举
     */
    UNKNOWN(0,"未知"),

    MALE(1,"男"),

    FEMALE(2,"女"),

    ;


    /**
     * 值
     */
    private Integer value;
    /**
     * 描述
     */
    private String desc;
}
