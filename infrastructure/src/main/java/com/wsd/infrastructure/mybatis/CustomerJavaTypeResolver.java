package com.wsd.infrastructure.mybatis;

import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * 处理自定义mysql字段与java字段类型映射
 * @author wsd
 * @date 2022/1/5
 */
public class CustomerJavaTypeResolver extends JavaTypeResolverDefaultImpl implements JavaTypeResolver {
    public CustomerJavaTypeResolver() {
        super();
        // tinyint转Integer
        this.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
