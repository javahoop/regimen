package com.wsd.infrastructure.controller.resolvor.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.springframework.core.MethodParameter;

import java.util.Objects;


/**
 * @author wsd
 * @date 2021年12月11日 09:53
 **/
public abstract class AbstractArgHandler {
    /**
     * 匹配排序的序号
     */
    private final Integer order;

    public AbstractArgHandler(Integer order) {
        this.order = order;
    }

    public final Integer getOrder() {
        return order;
    }

    /**
     * 验证值匹配,如果匹配返回true,如果返回false或者null视为不匹配,只有返回匹配后才会执行getValue
     * 
     * @param paramType 参数的类型
     * @return 返回是否匹配
     */
    public abstract Boolean match(Class<?> paramType);

    /**
     * 获取值
     * 
     * @param jsonTree 数据节点
     * @param parameter 方法参数
     * @param count 参数个数
     * @return 返回获取的值
     * @throws Exception 业务异常
     */
    public abstract Object getValue(JsonNode jsonTree, MethodParameter parameter, Integer count) throws Exception;

    /**
     * 判断是不是null,不存在或者值为null就是null.
     *
     * @param node
     * @return
     */
    public final boolean isNull(JsonNode node) {
        return node == null || Objects.equals(NullNode.getInstance(), node);
    }
}
