package com.example.proyecto_procesos.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SophyFarm API")
                        .version("1.0")
                        .description("Documentación de la API de SophyFarm")
                        .contact(new Contact()
                                .name("Juan David Ramírez")
                                .email("jdramirezsantana@ucundinamarca.edu.co"))
                );
    }
}
