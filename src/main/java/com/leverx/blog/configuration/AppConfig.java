package com.leverx.blog.configuration;

import com.leverx.BasePackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BasePackageMarker.class)
public class AppConfig {

}
