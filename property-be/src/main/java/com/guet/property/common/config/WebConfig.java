package com.guet.property.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dhxstart
 * @date 2022/4/21 15:44
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + fileSavePath);
        //System.out.println("file:"+fileSavePath);
    }

}
