package com.wsd.infrastructure.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 * @author wsd
 * @date 2021/5/25
 */
@Configuration
public class SwaggerConfig {
    /**
     * 文档标题
     */
    @Value("${swagger.title:}")
    private String title;
    /**
     * 文档描述
     */
    @Value("${swagger.description:}")
    private String description;
    /**
     * 文档版本
     */
    @Value("${swagger.version:}")
    private String version;
    /**
     * 是否启用swagger(在生产环境应该禁用)
     */
    @Value("${swagger.enable:true}")
    private boolean enable;
    @Bean
    public OpenAPI apiDocConfig() {
        // 初始化配置
        return new OpenAPI()
                // 配置文档信息
                .info(new Info().title(title)
                        .description(description)
                        .version(version));
                // 自动为所有api添加token请求头

                // 设置全局token配置功能
    }

    @Bean
    public GroupedOpenApi apiDocGroupConfig() {
        // 文档分组配置,开启分组才能使用knife4j
        return GroupedOpenApi.builder().group(title).pathsToMatch("/**").build();
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        // 采用与jackson一致的json处理方式,返回转换后的参数名(下划线自动支持)
        return new ModelResolver(objectMapper);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
