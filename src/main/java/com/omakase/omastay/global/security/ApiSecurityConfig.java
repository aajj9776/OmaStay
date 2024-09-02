package com.omakase.omastay.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

    //autowired 하면 되지만 시스템 문제로 ApiSecurityConfig 이거보다 JwtAuthorizationFilter이게
    // 먼저 실행되면 안되니까 상수로 지정한다.

    //final로 지정해야 RequiredArgsConstructor에 의해 자동대입 된다.
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception{
        http
            .securityMatcher("/api/**") //설정된 경로로 들어오는 모든 것들을 검사함
            .authorizeHttpRequests( //요청에 대한 권한을 지정
                authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/api/*/bbs/**","/api/member/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/member/login").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/member/logout").permitAll()
                    .anyRequest().authenticated() //인증이 되어야 함
            )
            .csrf(
                csrf -> csrf.disable()
            ) // csrf토큰을 꺼야 한다. 요청시 항상 토큰을 태워서 요청해야 한다.
            .httpBasic(
                httpBasic -> httpBasic.disable()
            ) //httpBasic로그인 방식 끄기
            .formLogin(
                formLogin -> formLogin.disable()
            )//폼로그인 방식 끄기
            .sessionManagement(
                sessionManagement -> sessionManagement.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS)
            )//세션 로그인 방식 끄기
            .addFilterBefore(jwtAuthorizationFilter,
             UsernamePasswordAuthenticationFilter.class
             );
        return http.build();
    }
}
