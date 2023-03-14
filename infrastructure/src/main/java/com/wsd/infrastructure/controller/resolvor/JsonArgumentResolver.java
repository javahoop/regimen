package com.wsd.infrastructure.controller.resolvor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.wsd.infrastructure.constants.Constants;
import com.wsd.infrastructure.controller.resolvor.handler.AbstractArgHandler;
import com.wsd.infrastructure.filter.RequestWrapper;
import com.wsd.infrastructure.resolver.JsonRequestBody;
import com.wsd.infrastructure.utils.CommonUtil;
import com.wsd.infrastructure.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @date 2021-03-23
 * @description 描述
 */
@Slf4j
@Component
public class JsonArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 请求参数记录
     */
    private static volatile Map<String, Integer> paramCountMap = new HashMap<>(64);
    /**
     * 缓存参数解析
     */
    private static volatile Map<String, AbstractArgHandler> argResolverMap = new HashMap<>(64);
    @Autowired
    private ArgResolverFactory argResolverFactory;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 支持post请求，get请求在url里面解析器无法解析出值
        boolean isRequestPost = false;
        if (!parameter.hasMethodAnnotation(PostMapping.class)) {
            RequestMapping re = parameter.hasMethodAnnotation(RequestMapping.class)
                    ? parameter.getMethod().getAnnotation(RequestMapping.class)
                    : null;
            if (re != null && re.method().length > 0) {
                for (RequestMethod m : re.method()) {
                    if (RequestMethod.POST == m) {
                        isRequestPost = true;
                        break;
                    }
                }
            }
        } else {
            isRequestPost = true;
        }
        return isRequestPost
                && (AnnotatedElementUtils.hasAnnotation(parameter.getContainingClass(), JsonRequestBody.class)
                        || parameter.hasMethodAnnotation(JsonRequestBody.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        JsonNode jsonTree = getJson(request);
        Integer count = getParamCount(request, parameter);
        AbstractArgHandler resolver;
        if (Objects.nonNull(resolver = argResolverMap.get(parameter.getParameter().getType().getName()))) {
            return validateAndReturn(resolver.getValue(jsonTree, parameter, count), parameter, mavContainer, webRequest,
                    binderFactory);
        } else {
            if (WebRequest.class.isAssignableFrom(parameter.getParameterType())) {
                if (!parameter.getParameterType().isInstance(webRequest)) {
                    throw new IllegalStateException("Current request is not of type ["
                            + parameter.getParameterType().getName() + "]: " + webRequest);
                }
                return webRequest;
            } else if (ServletRequest.class.isAssignableFrom(parameter.getParameterType())
                    || MultipartRequest.class.isAssignableFrom(parameter.getParameterType())) {
                // ServletRequest / HttpServletRequest / MultipartRequest / MultipartHttpServletRequest
                return webRequest.getNativeRequest(parameter.getParameterType());
            } else if (ServletResponse.class.isAssignableFrom(parameter.getParameterType())) {
                return webRequest.getNativeResponse(parameter.getParameterType());
            } else {
                resolver = argResolverFactory.getArgResolver(parameter);
                if (Objects.isNull(resolver)) {
                    throw new IllegalArgumentException("暂不支持的参数类型");
                } else {
                    argResolverMap.put(parameter.getParameter().getType().getName(), resolver);
                    return validateAndReturn(resolver.getValue(jsonTree, parameter, count), parameter, mavContainer,
                            webRequest, binderFactory);
                }
            }
        }
    }

    /**
     * 参数验证
     *
     * @param value
     * @param parameter
     * @return 验证后的值
     */
    public final Object validateAndReturn(Object value, MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (binderFactory != null) {
            WebDataBinder binder = binderFactory.createBinder(webRequest, value, parameter.getParameterName());
            if (value != null) {
                validateIfApplicable(binder, parameter);
                if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                    throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
                }
            }
            if (mavContainer != null) {
                mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + parameter.getParameterName(),
                        binder.getBindingResult());
            }
        }
        return value;
    }

    private void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        Annotation[] annotations = parameter.getParameterAnnotations();
        for (Annotation ann : annotations) {
            Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
            if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
                Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
                Object[] validationHints = (hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
                binder.validate(validationHints);
                break;
            }
        }
    }

    private boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
        int i = parameter.getParameterIndex();
        Class<?>[] paramTypes = parameter.getExecutable().getParameterTypes();
        boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
        return !hasBindingResult;
    }

    /**
     * 获取当前可用的参数类型
     *
     * @param parameter
     * @return
     */
    private Integer getParamCount(HttpServletRequest request, MethodParameter parameter) {
        String path = request.getServletPath();
        Integer count;
        if (Objects.isNull(count = paramCountMap.get(path))) {
            count = parameter.getMethod().getParameterCount();
            for (Class<?> pt : parameter.getMethod().getParameterTypes()) {
                if (WebRequest.class.isAssignableFrom(pt)) {
                    count = count - 1;
                }
                if (ServletRequest.class.isAssignableFrom(pt) || MultipartRequest.class.isAssignableFrom(pt)) {
                    count = count - 1;
                }
                if (ServletResponse.class.isAssignableFrom(pt)) {
                    count = count - 1;
                }
            }
            paramCountMap.put(path, count);
        }
        return count;
    }

    /**
     * 获取请求体中的json字符串
     *
     * @param request 请求参数
     * @return json字符串
     */
    private JsonNode getJson(HttpServletRequest request) {
        Object jv = request.getAttribute(Constants.REQUEST_JSON_VALUE);
        if (Objects.nonNull(jv)) {
            return (JsonNode) jv;
        }
        String body = null;
        if (request instanceof RequestWrapper) {
            body = ((RequestWrapper) request).getBody();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;

            try (InputStream inputStream = request.getInputStream()) {
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    char[] charBuffer = new char[512];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
                throw new RuntimeException("读取请求数据异常");
            }
            body = stringBuilder.toString();
        }
        try {
            JsonNode node = CommonUtil.isNotBlank(body) ? JsonUtil.get().readTree(body) : NullNode.getInstance();
            request.setAttribute(Constants.REQUEST_JSON_VALUE, node);
            return node;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("请求BODY不是合法的JSON对象");
        }
    }
}
