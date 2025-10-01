package com.lyrics.ecs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 对所有接口生效
                // 允许前端域名（Spring Boot 2.4+ 推荐用allowedOriginPatterns，支持通配符且兼容credentials）
                .allowedOriginPatterns("http://localhost:6190")
                // 明确允许OPTIONS方法（预检请求必须）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头，必须包含自定义的token
                .allowedHeaders("token", "Content-Type", "Accept")
                // 允许前端获取的响应头（可选，按需添加）
                .exposedHeaders("token", "Content-Disposition")
                // 允许携带Cookie（必须为true，否则token等认证信息可能被浏览器拦截）
                .allowCredentials(true)
                // 预检请求缓存时间（秒），减少重复预检
                .maxAge(3600);  // 1小时内不再发送预检请求
    }
}
