/*
 *  Copyright (C), 2014-2016, sanlion.do|izuanqian.com
 */
package com.pz.api.apiconfig;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan({"com.pz.api.apiconfig"})
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Value("${apidoc}")
    boolean onApiDoc;

    @Bean
    public SwaggerSpringMvcPlugin v320() {
        return mvcPlugin(ApiConfiguration.Version.v320);
    }

    @Bean
    public SwaggerSpringMvcPlugin v110() {
        return mvcPlugin(ApiConfiguration.Version.v110);
    }

    @Bean
    public SwaggerSpringMvcPlugin base() {
        return mvcPlugin(ApiConfiguration.Version.base);
    }

    private SwaggerSpringMvcPlugin mvcPlugin(ApiConfiguration.Version version) {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(
                        new ApiInfo(version._description()
                                + " —— WebApi在线文档|测试工具",
                                version._description(),
                                null, null, null, null))
                .enable(onApiDoc)
                .swaggerGroup(version._name())
                .useDefaultResponseMessages(false)
                .includePatterns(
                        ApiConfiguration.paths(version)
                );
    }
}
