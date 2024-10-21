package net.akarmanov.projectplace.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi baseOpenAPI() {
        return GroupedOpenApi.builder()
                .group("main")
                .pathsToMatch("/api/v1/**")
                .pathsToExclude("/api/v1/admin/**")
                .addOpenApiCustomizer(openAPI -> openAPI
                        .info(new Info().title("Project Place API").version("1.0"))
                        .schemaRequirement("bearerAuth", new SecurityScheme()
                                .type(Type.HTTP)
                                .scheme("bearer")
                                .name("Authorization")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
                .build();
    }

    @Bean
    public GroupedOpenApi adminAPI() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/v1/admin/**")
                .pathsToMatch("/api/v1/auth/**")
                .packagesToScan("net.akarmanov.projectplace.rest.api.admin", "net.akarmanov.projectplace.rest.api.auth")
                .addOpenApiCustomizer(openAPI -> openAPI
                        .info(new Info().title("Project Place Admin API").version("1.0"))
                        .schemaRequirement("bearerAuth", new SecurityScheme()
                                .type(Type.HTTP)
                                .scheme("bearer")
                                .name("Authorization")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
                .build();
    }
}