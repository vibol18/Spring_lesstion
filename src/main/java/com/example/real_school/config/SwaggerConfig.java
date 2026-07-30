package com.example.real_school.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI schoolManagementOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("School Management System API")
                .description("REST API for School Management System")
                .version("1.0.0")
                .contact(new Contact()
                    .name("School Admin")
                    .email("admin@school.com")))
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .schemaRequirement("Bearer Authentication",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .bearerFormat("JWT")
                    .scheme("bearer"));
    }
}
