package com.leverx.blog.configuration;

import com.leverx.BasePackageMarker;
import com.leverx.blog.exception.handlers.HandlerExceptionResolverProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = "com.leverx.blog.controller")
@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public HandlerExceptionResolverProxy handlerExceptionResolverProxy(@Autowired HandlerExceptionResolver handlerExceptionResolver) {
        return new HandlerExceptionResolverProxy(handlerExceptionResolver);
    }

}
