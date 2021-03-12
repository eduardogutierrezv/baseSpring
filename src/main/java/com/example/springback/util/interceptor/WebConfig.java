package com.example.springback.util.interceptor;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
// @Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public PrePostHandlerInterceptor prePostHandlerInterceptor() {
        return new PrePostHandlerInterceptor();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(prePostHandlerInterceptor());
    }
}