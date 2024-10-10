package com.omakase.omastay.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false); // 세션을 가져옴, 없으면 null

        if (session == null || session.getAttribute("adminMember") == null) {
            // 세션이 없거나 사용자 정보가 없으면 sessionExpired 뷰로 리다이렉트
            response.sendRedirect("/admin/login?error=sessionExpired");
            return false; // 요청 처리를 중단
        }
        return true; // 요청 처리를 계속 진행
    }
}
