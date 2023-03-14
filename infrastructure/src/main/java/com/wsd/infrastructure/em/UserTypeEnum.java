package com.wsd.infrastructure.em;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吴松达
 * @date 2022/4/18 14:46
 * @Description:
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserTypeEnum {
    /**
     * 性别枚举
     */
    UNKNOWN,

    DOCTOR,

    STORE,

    CUSTOMER,
            ;

}
