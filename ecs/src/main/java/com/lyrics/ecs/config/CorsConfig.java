package com.lyrics.ecs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // 允许所有接口
                        .allowedOriginPatterns("*")  // 允许所有来源（SpringBoot 2.4+ 推荐用 allowedOriginPatterns）
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)  // 允许携带 cookie
                        .maxAge(3600); // 预检请求的缓存时间（秒）
            }
        };
    }
}
