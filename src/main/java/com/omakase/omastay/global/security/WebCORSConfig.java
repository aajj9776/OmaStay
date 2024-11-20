package com.omakase.omastay.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCORSConfig implements WebMvcConfigurer { // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9090", "http://omastay.duckdns.org", "http://www.omastay.duckdns.org, https://www.omastay.duckdns.org, https://omastay.duckdns.org") // 특정 출처만 허용
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true); // 인증 정보 허용
    }
}
