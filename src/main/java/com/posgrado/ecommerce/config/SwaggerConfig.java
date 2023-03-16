package com.posgrado.ecommerce.config;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  public static final String VERSION = "1.0.0";
  public static final String TITLE = "Ecommerce API";
  public static final String DESCRIPTION_API = "Api Ecommerce Posgrado";
  public static final String API_OWNER = "Posgrado";
  public static final String OWNER_WEB_SITE = "www.posgrado.com";
  public static final String OWNER_EMAIL = "posgrado@gmail.com";
  public static final String API_BASE_PACKAGE = "com.posgrado.ecommerce.controller";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .securitySchemes(List.of(securityScheme()))
        .securityContexts(Collections.singletonList(securityContext()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .version(VERSION)
        .title(TITLE)
        .description(DESCRIPTION_API)
        .contact(new Contact(API_OWNER, OWNER_WEB_SITE, OWNER_EMAIL))
        .build();
  }

  private ApiKey securityScheme() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(securityReferenceList())
        .forPaths(PathSelectors.ant("/api/v1/auth/**").negate())
        .build();
  }

  private List<SecurityReference> securityReferenceList() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "Access everything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
    return List.of(new SecurityReference("JWT", authorizationScopes));
  }
}
