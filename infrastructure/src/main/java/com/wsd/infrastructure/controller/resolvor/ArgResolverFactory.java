package com.wsd.infrastructure.controller.resolvor;


import com.wsd.infrastructure.controller.resolvor.handler.AbstractArgHandler;
import com.wsd.infrastructure.utils.CommonUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wsd
 * @date 2021年12月11日 10:06
 **/
@Component
public class ArgResolverFactory implements ApplicationContextAware {
    private static List<AbstractArgHandler> ARG_RESOLVER_COLLECT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractArgHandler> argResolvers = applicationContext.getBeansOfType(AbstractArgHandler.class);
        if (CommonUtil.isNotEmpty(argResolvers)) {
            ARG_RESOLVER_COLLECT = argResolvers.values().parallelStream()
                    .sorted(Comparator.comparingInt(AbstractArgHandler::getOrder)).collect(Collectors.toList());
        } else {
            ARG_RESOLVER_COLLECT = new ArrayList<>(0);
        }
    }

    public AbstractArgHandler getArgResolver(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        for (AbstractArgHandler argResolver : ARG_RESOLVER_COLLECT) {
            if (argResolver.match(paramType)) {
                return argResolver;
            }
        }
        return null;
    }
}
