package com.ticket.concert.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SwaggerConfig implements WebMvcOpenApiTransformationFilter {

    private static final Contact DEFAULT_CONTACT = new Contact("EDTS",
        "https://www.sg-edts.com/", "soni.setiabudi@gmail.com");

    @Value("${swagger.url}")
    private String swaggerHost;

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }
    
    @Bean
    public Docket apiAllDocket() {
        return new Docket(DocumentationType.OAS_30)
            .groupName("All API")
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
            .host(swaggerHost)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Elevenia Digital Technology Service (EDTS)")
            .description("EDTS API")
            .contact(DEFAULT_CONTACT)
            .version("1.0.0")
            .build();
    }

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI swagger = context.getSpecification();
        Server server = new Server();
        server.setUrl(swaggerHost);
        swagger.setServers(Arrays.asList(server));
        return swagger;
  }

    @Override
    public boolean supports(DocumentationType docType) {
        return docType.equals(DocumentationType.OAS_30);
    }
}

