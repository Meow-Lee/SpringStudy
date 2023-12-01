package com.example.study_spring.Ch03.web;

import com.example.study_spring.Ch03.config.CourtConfiguration;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Set;

/**
 * DispatcherServlet은 스프링 MVC의 핵심 서블릿 클래스로, 웹 요청을 받아 적절한 핸들러에 송부
 * 서블리스이 이름을 court라 짓고, /가 포함된 모든 URL을 매핑
 */

public class CourtServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(CourtConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic courtRegistration = ctx.addServlet("court", dispatcherServlet);
        courtRegistration.setLoadOnStartup(1);
        courtRegistration.addMapping("/");
    }
}
