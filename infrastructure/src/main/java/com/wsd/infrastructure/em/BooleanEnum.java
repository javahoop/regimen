package com.wsd.infrastructure.em;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吴松达
 * @ClassName BooleanEnum.java
 * @Description Boolean枚举
 * @createTime 2022年01月20日 17:57:00
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BooleanEnum implements AbstractEnum<BooleanEnum>{
    /**
     * 否
     */
    FALSE(0,"否"),
    /**
     * 是
     */
    TURE(1,"是"),
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
