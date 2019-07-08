package com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yangcheng
 * @ClassName:swagger配置类
 * @Description:配置swgger
 * @date 2019年06月03日 9:28
 */
@Configuration
public class Swagger {
    @Bean
    public Docket createResApi(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.controller"))
                .paths(PathSelectors.any()).build();
        return docket;
    }
    @Bean
    public ApiInfo apiInfo(){
        ApiInfo apiInfo=new ApiInfoBuilder()
                .title("cbd停车系统api接口")
                .description("前台和后台间交互所用api")
                .termsOfServiceUrl("https://www.cdbsystem.com")
                .version("1.0").build();
        return apiInfo;
    }
}
