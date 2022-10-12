package com.example.interceptores_http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("horarioAperturaInterceptor")
    private HandlerInterceptor horarioApertura;

    @Autowired
    @Qualifier("horarioCierreInterceptor")
    private HandlerInterceptor horarioCierre;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry.addInterceptor(horarioApertura)
        .excludePathPatterns("/cerrado");

        registry.addInterceptor(horarioCierre)
        .excludePathPatterns("/index");
    }
    
}
