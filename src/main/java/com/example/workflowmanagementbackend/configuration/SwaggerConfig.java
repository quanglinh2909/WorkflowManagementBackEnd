package com.example.workflowmanagementbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.edu.fistspring.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
  }
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Dev cars2")
            .description("Dev cars api")
            .termsOfServiceUrl("${url.swagger}")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
            .version("1.0")
            .build();
  }
}