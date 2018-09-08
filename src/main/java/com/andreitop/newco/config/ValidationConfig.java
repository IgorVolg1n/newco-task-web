package com.andreitop.newco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan("com.andreitop.newco")
public class ValidationConfig {

    @Bean
    public Validator validationFactory() {
        return new LocalValidatorFactoryBean();
    }
}
