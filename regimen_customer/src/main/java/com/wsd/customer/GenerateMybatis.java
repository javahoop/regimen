package com.wsd.customer;


import com.wsd.infrastructure.mybatis.Generator;

/**
 * MyBatis生成器,运行后会重新生成所有数据表对应的实体和基础操作
 * @author wsd
 * @date 2021/12/28
 */
public class GenerateMybatis {
    private static final String FILE_PATH = "regimen_customer/src/main/java";
    public static void main(String[] args) {
        // 需要生成的表名,参数为数组
        String[] tables = {"commodity","commodity_option"};

        Generator.create("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://139.9.48.18:3306/object?useUnicode=true&characterEncoding=utf-8&useSSL=true&allowMultiQueries=true&serverTimezone=Asia/Shanghai",
                "root",
                "wsd120418",
                ".customer",
                // 生成相关的数据表
                tables,FILE_PATH
        );
    }
}
