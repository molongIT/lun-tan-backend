package com.pxl.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration//配置类
@EnableSwagger2//Swagger注解
public class SwaggerConfig {

    //api接口扫描路径
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.pxl.controller";

    private static final String VERSION = "1.0.0";

    @Bean
    public Docket webApiconfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("模板项目后端接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求。
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("莉莉安")//设置文档的标题
                .description("莉莉安API接口文档")//设置文档的描述
                .version(VERSION)//设置文档的版本信息
                .build();
    }
}
