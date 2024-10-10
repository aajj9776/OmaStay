package com.omakase.omastay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.omakase.omastay.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private LoginInterceptor loginInterceptor; // 인터셉터 주입

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**") // 모든 경로에 대해 적용
                .excludePathPatterns("/admin/login", "/admin/login/validate"); // 로그인 및 세션 만료 페이지는 제외
    }
}
