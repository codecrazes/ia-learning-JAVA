package br.com.fiap.ia_learning.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {

        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**")
                .addOperationCustomizer((operation, handlerMethod) -> {

                    operation.addParametersItem(
                            new io.swagger.v3.oas.models.parameters.Parameter()
                                    .in("header")
                                    .required(false)
                                    .name("Accept-Language")
                                    .description("Idioma da resposta (pt-BR, en)")
                                    .schema(new StringSchema()._default("pt-BR"))
                    );

                    return operation;
                })
                .build();
    }

    @Bean
    public OpenAPI openAPI() {

        Parameter languageHeader = new Parameter()
                .in("header")
                .required(false)
                .name("Accept-Language")
                .description("Idioma da resposta (pt-BR, en)")
                .schema(new StringSchema()._default("pt-BR"));

        return new OpenAPI()
                .info(new Info()
                        .title("IA Learning API")
                        .version("1.0")
                        .description("API com suporte a internacionalização"))
                .components(new Components()
                        .addParameters("Accept-Language", languageHeader)
                );
    }
}
