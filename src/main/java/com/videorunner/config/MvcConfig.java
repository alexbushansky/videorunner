package com.videorunner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("@{upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/web-file/**")
                .addResourceLocations("classpath:/web-file/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("classpath:/uploads/");

        registry.addResourceHandler("/video/**")
                .addResourceLocations("file://" + uploadPath + "/");
    }


}