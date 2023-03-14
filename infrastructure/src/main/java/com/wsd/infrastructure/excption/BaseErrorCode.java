package com.wsd.infrastructure.excption;


import com.wsd.infrastructure.utils.CommonUtil;

/**
 * @author wsd
 * @date 2021年12月05日 15:23
 **/
public interface BaseErrorCode {
    /**
     * 获取错误编码
     *
     * @return 返回错误编码
     */
    String getCode();

    /**
     * 获取错误描述
     *
     * @return 返回错误描述
     */
    String getDesc();

    /**
     * 获取运行时抛出的异常
     *
     * @return 返回构建的异常
     */
    default BizRunTimeException getBizRunTimeException() {
        return new BizRunTimeException(getCode(), getDesc());
    }

    /**
     * 指定运行时错误描述信息
     *
     * @param msg 本次异常的错误描述信息
     * @return 返回错误描述信息
     */
    default BizRunTimeException getBizRunTimeException(String msg) {
        if (CommonUtil.isBlank(msg)) {
            msg = getDesc();
        }
        return new BizRunTimeException(getCode(), msg);
    }

    /**
     * 抛出运行时业务异常
     *
     * @param msg 本次异常的错误描述信息
     * @throws BizRunTimeException 抛出的业务异常
     */
    default void throwBizRunTimeException(String msg) throws BizRunTimeException {
        throw getBizRunTimeException(msg);
    }

    /**
     * 抛出运行时业务异常
     *
     * @throws BizRunTimeException 抛出的业务异常
     */
    default void throwBizRunTimeException() throws BizRunTimeException {
        throw getBizRunTimeException();
    }
    /**
     * 获取抛出的异常
     *
     * @return 返回构建的异常
     */


    /**
     * 指定错误描述信息
     *
     * @param msg 本次异常的错误描述信息
     * @return 返回错误描述信息
     */


    /**
     * 抛出业务异常
     *
     * @param msg 本次异常的错误描述信息
     * @throws BizException 抛出的业务异常
     */

    /**
     * 抛出业务异常
     *
     * @throws BizException 抛出的业务异常
     */

}
