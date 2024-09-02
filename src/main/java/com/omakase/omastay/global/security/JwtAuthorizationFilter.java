package com.omakase.omastay.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    //JWT토큰을 가지고 서버에 들어오는 요청을 
    // 허용하기 위한 인가(authorization)처리를 하는
    // Filter객체다.
    @Override
    @SneakyThrows// try~catch로 예외처리를 해야할 것을 명시적 예외처리를 생략할 수 있도록 함
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //여기 if문에 있는 url(로그인 로그아웃)은 통과시킨다.
        if( request.getRequestURI().equals("/api/member/login") || 
        request.getRequestURI().equals("/api/member/logout")){
            filterChain.doFilter(request, response);
            return;
        }

        //accessToken검증 또는 refreshToken발급
        String accessToken = "";
        // accessToken이 비었냐?
        if( !accessToken.isBlank()){
            //검증
        }
        filterChain.doFilter(request, response);

    }
}
//돈 많으면 '형'
