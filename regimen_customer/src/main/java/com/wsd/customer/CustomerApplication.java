package com.wsd.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author wsd
 * @date 2021年05月22日 15:34
 **/
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(scanBasePackages = "com.wsd")
public class CustomerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
