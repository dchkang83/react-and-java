package com.test.spring_boot_demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
        .title("Spring API 문서")
        .version("v1.0")
        .description("Spring Boot API 설명 문서")
        .contact(new Contact()
            .name("개발자")
            .email("developer@example.com"));

    return new OpenAPI()
        .info(info);
  }

  @Bean
  public GroupedOpenApi group1() {
    return GroupedOpenApi.builder()
        .group("1. Version 1")
        // .pathsToMatch("/api/users/**")
        .pathsToMatch("/v1/**")
        .build();
  }

}
