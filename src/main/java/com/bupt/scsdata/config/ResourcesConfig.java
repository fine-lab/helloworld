package com.bupt.scsdata.config;

import com.sun.istack.internal.NotNull;
//import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    final  AppProperties appProperties;
    public ResourcesConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
/*        registry.addResourceHandler("/files/**").addResourceLocations("file:"+appProperties.getFileDirectory());
        registry.addResourceHandler("/login/**").addResourceLocations("classpath:/login/");
        registry.addResourceHandler("/teacher/**").addResourceLocations("classpath:/teacher/");
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");*/
    }
}