package com.example.study_spring.Ch03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * com.example.study_spring.Ch03 패키지를 스캐닝하여 빈들을 등록하도록 지시
 */
@Configuration
@ComponentScan("com.example.study_spring.Ch03")
public class CourtConfiguration {
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
