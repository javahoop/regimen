package com.wsd.infrastructure.mybatis;


import com.wsd.infrastructure.mybatis.constant.ColumnOverrideEnum;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis生成器
 * @author wsd
 * @date 2021/12/26
 */
@Slf4j
public class Generator {
    private Generator() {}

    /**
     * 生成文件包路径
     */
    private static final String PACKAGE_PATH = "com.wsd";


    /**
     * 生成实体类,DAO,MAPPER
     * @param dbDriver jdbc驱动
     * @param dbUrl jdbc连接url
     * @param dbUser 数据库用户名
     * @param dbPass 数据库密码
     * @param moduleName 模块名,例如business-user,模块名为user
     */
    public static void
    create(String dbDriver, String dbUrl, String dbUser, String dbPass, String moduleName, String[] tableNames,String FILE_PATH) {
        List<String> warnings = new ArrayList<>();
        try {
            // 开启覆盖模式,每次执行都会覆盖已有的文件
            boolean overwrite = true;
            Configuration config = new Configuration();
            // model生成模式配置,null为默认配置,默认值为CONDITIONAL,当出现2个或以上text字段或json字段时会自动拆分出XXXWithBLOBs文件,
            // 如果配置为FLAT则不拆分blobs,配置为HIERARCHICAL则会一律拆分,包括主键也不能用Long类型传递,为生成单独的XXXKey类代表主键
            Context context = new Context(ModelType.FLAT);
            // 设置生成器标识
            context.setId("mysql");
            // 设置生成目标类型
            context.setTargetRuntime("Mybatis3");
            // 启用Serializable支持
            context.addPluginConfiguration(enablePlugin("org.mybatis.generator.plugins.SerializablePlugin"));
            // 启用toString支持
            context.addPluginConfiguration(enablePlugin("org.mybatis.generator.plugins.ToStringPlugin"));
            // 启用单条数据查询插件
            context.addPluginConfiguration(enablePlugin("com.itfsw.mybatis.generator.plugins.SelectOneByExamplePlugin"));
            // 启用Example Criteria 增强插件
            context.addPluginConfiguration(enablePlugin("com.itfsw.mybatis.generator.plugins.ExampleEnhancedPlugin"));
            // 启用批量插入插件
            context.addPluginConfiguration(enablePlugin("com.itfsw.mybatis.generator.plugins.BatchInsertPlugin"));
            // 启用数据Model属性对应Column获取插件
            context.addPluginConfiguration(enablePlugin("com.itfsw.mybatis.generator.plugins.ModelColumnPlugin"));
            // 启用逻辑删除
            PluginConfiguration logicalDeletePlugin = new PluginConfiguration();
            logicalDeletePlugin.setConfigurationType("com.itfsw.mybatis.generator.plugins.LogicalDeletePlugin");
            logicalDeletePlugin.addProperty("logicalDeleteColumn", "is_deleted");
            logicalDeletePlugin.addProperty("logicalDeleteValue", "1");
            logicalDeletePlugin.addProperty("logicalUnDeleteValue", "0");
            // 关闭逻辑删除自定义常量
            logicalDeletePlugin.addProperty("enableLogicalDeleteConst", "false");
            context.addPluginConfiguration(logicalDeletePlugin);
            // 启用选择性返回插件
            context.addPluginConfiguration(enablePlugin("com.itfsw.mybatis.generator.plugins.SelectSelectivePlugin"));


            // 启用自定义注释生成器
            CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
            commentGeneratorConfiguration.setConfigurationType("com.wsd.infrastructure.mybatis.CustomCommentGenerator");
            // 开启备注注释
            commentGeneratorConfiguration.addProperty("addRemarkComments", "true");
            // 不屏蔽系统注释,这里不能为true,关闭系统注释后无法覆盖xml mapper文件
            commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
            context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

            // 配置jdbc连接器
            context.setJdbcConnectionConfiguration(getJdbcConnectionConfig(dbDriver, dbUrl, dbUser, dbPass));

            // 配置自定义字段类型映射
            JavaTypeResolverConfiguration javaTypeResolverConfig = new JavaTypeResolverConfiguration();
            javaTypeResolverConfig.setConfigurationType("com.wsd.infrastructure.mybatis.CustomerJavaTypeResolver");
            context.setJavaTypeResolverConfiguration(javaTypeResolverConfig);

            // 配置model生成器
            JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
            // 生成目标package为当前项目模块下的model.generate包
            javaModelGeneratorConfiguration.setTargetPackage(PACKAGE_PATH + moduleName + ".model.generate");
            // 设置model生成目录
            javaModelGeneratorConfiguration.setTargetProject(FILE_PATH);
            // 如果是多个schema的情况,按schema生成子包
            javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
            // getter方法中，对String类型字段调用trim()
            javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

            // 配置Mapper xml生成器
            SqlMapGeneratorConfiguration sqlMapGeneratorConfig = new SqlMapGeneratorConfiguration();
            // 设置xml生成目录
            sqlMapGeneratorConfig.setTargetProject(FILE_PATH);
            // 设置生成mapper xml对应的包
            //目标包(*Mapper.xml类文件存放包)
            sqlMapGeneratorConfig.setTargetPackage(PACKAGE_PATH + moduleName + ".mapper.generate");
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfig);

            // 配置java client生成器,用于生成dao,配合mapper生成器使用
            JavaClientGeneratorConfiguration javaClientGeneratorConfig = new JavaClientGeneratorConfiguration();
            // 生成类型,主要有ANNOTATEDMAPPER、MIXEDMAPPER、XMLMAPPER，需要与Context的TargetRuntime配合
            //JavaClient生成器类型()
            javaClientGeneratorConfig.setConfigurationType("XMLMAPPER");
            // 设置dao生成目录
            //目标项目(源码主路径)
            javaClientGeneratorConfig.setTargetProject(FILE_PATH);
            // 设置dao生成的包
            //目标包(*Mapper.java类文件存放包)
            javaClientGeneratorConfig.setTargetPackage(PACKAGE_PATH + moduleName + ".dao.generate");
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);


            // 数据表配置
            for (String tableName : tableNames) {
                TableConfiguration tableConfiguration = new TableConfiguration(context);
                tableConfiguration.setTableName(tableName);
                // 配置启用主键,固定主键为id字段,采用JDBC类型对应xml中配置的useGeneratedKeys="true"选项
                GeneratedKey generatedKey = new GeneratedKey("id", "JDBC", true, "post");
                tableConfiguration.setGeneratedKey(generatedKey);
                // 配置字段映射
                for (ColumnOverrideEnum columnOverride : ColumnOverrideEnum.values()) {
                    ColumnOverride override = generateColumnOverride(columnOverride.getFrom(), columnOverride.getTo());
                    tableConfiguration.addColumnOverride(override);
                }

                // 激活表映射关系配置
                context.addTableConfiguration(tableConfiguration);
            }

            config.addContext(context);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            log.error("MyBatis文件生成失败", e);
            Thread.currentThread().interrupt();
        }
    }
    /**
     * 生成字段映射对象
     * @param from 原始字段名
     * @param to 目标字段名
     * @return 字段映射对象
     */
    private static ColumnOverride generateColumnOverride(String from, String to) {
        ColumnOverride columnOverride = new ColumnOverride(from);
        columnOverride.setJavaProperty(to);
        return columnOverride;
    }
    /**
     * 启用指定插件支持
     * @param param 启用参数
     * @return 插件配置对象
     */
    private static PluginConfiguration enablePlugin(String param) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType(param);
        return pluginConfiguration;
    }
    /**
     * 获取jdbc连接器配置
     * @param dbDriver jdbc驱动
     * @param dbUrl jdbc连接url
     * @param dbUser 数据库用户名
     * @param dbPass 数据库密码
     * @return jdbc配置对象
     */
    private static JDBCConnectionConfiguration getJdbcConnectionConfig(String dbDriver, String dbUrl, String dbUser, String dbPass) {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(dbDriver);
        jdbcConnectionConfiguration.setConnectionURL(dbUrl);
        jdbcConnectionConfiguration.setUserId(dbUser);
        jdbcConnectionConfiguration.setPassword(dbPass);
        // 该属性是为了通配符数据表全量同步模式下只生成当前数据库的表,不加这个属性会生成所有库的表
        jdbcConnectionConfiguration.addProperty("nullCatalogMeansCurrent", "true");
        return jdbcConnectionConfiguration;
    }
}
