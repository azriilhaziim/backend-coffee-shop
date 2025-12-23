//package com.accenture.ws.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // Allow all endpoints under /api
//                .allowedOrigins("http://localhost:2002") // Specify the new frontend port
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed methods
//                .allowedHeaders("*"); // Allow all headers
//    }
//}