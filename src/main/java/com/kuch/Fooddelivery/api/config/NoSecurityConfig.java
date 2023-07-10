package com.kuch.Fooddelivery.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/**
 * @author Artur Kuch
 */

@Configuration
@Profile("test")
public class NoSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return webSecurity -> webSecurity.ignoring().anyRequest();
    }

}
