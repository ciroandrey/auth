package com.forastero.auth.config;

import org.springframework.context.annotation.Bean;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**")
                .build();
    }

}
