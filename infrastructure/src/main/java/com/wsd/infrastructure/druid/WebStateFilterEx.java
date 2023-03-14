package com.wsd.infrastructure.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 吴松达
 * @ClassName WebStateFilterEx.java
 * @Description 解决druid无法显示uri监控bug
 * @createTime 2021年11月22日 00:01:00
 */
@Slf4j
@Component
public class WebStateFilterEx extends WebStatFilter {

    //private static Logger logger = LoggerFactory.getLogger(JmonitorCustomListener.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        ClassLoader duridMouldeClassLoader = DruidDataSource.class.getClassLoader();
        super.init(config);

        try {
            Class<?> webAppStatManager = Class.forName("com.alibaba.druid.support.http.stat.WebAppStatManager", true, duridMouldeClassLoader);
            Field field = ReflectionUtils.findField(webAppStatManager, "instance");
            field.setAccessible(true);

            Object webAppStatManagerObject = ReflectionUtils.getField(field, null);

            Method method = ReflectionUtils.findMethod(webAppStatManager, "addWebAppStatSet", Object.class);
            ReflectionUtils.makeAccessible(method);

            ReflectionUtils.invokeMethod(method, webAppStatManagerObject, this.webAppStat);
        } catch (Exception e) {

            log.error("load class error", e);
        }
    }
}