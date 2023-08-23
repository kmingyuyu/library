package com.example.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/* String uploadPath = "file:///C:/library/"; */
	
	@Value("${uploadPath}")
	String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**")
		.addResourceLocations(uploadPath);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(("/**"))
	    .allowedOrigins("*")
	    .allowedMethods("GET", "POST","PUT", "DELETE");
	} 
	
	
	
}
