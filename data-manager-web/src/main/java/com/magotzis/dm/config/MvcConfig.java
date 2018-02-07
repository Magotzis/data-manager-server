package com.magotzis.dm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dengyq on 17:00 2018/1/17
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/blank").setViewName("blank");
        registry.addViewController("/component/header").setViewName("component/header");
        registry.addViewController("/component/footer").setViewName("component/footer");
        registry.addViewController("/component/sidebar").setViewName("component/sidebar");
        registry.addViewController("/component/quickSidebar").setViewName("component/quickSidebar");
    }
}
