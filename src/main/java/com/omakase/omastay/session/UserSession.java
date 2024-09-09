package com.omakase.omastay.session;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Component
public class UserSession {
    
    private static final String SESSION_LOGIN_STATUS = "User";

    public void createSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_LOGIN_STATUS, true);  // 로그인 성공 여부 저장
    }

    // 로그인 상태 확인
    public boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        Boolean isLoggedIn = (Boolean) session.getAttribute(SESSION_LOGIN_STATUS);
        return isLoggedIn != null && isLoggedIn;
    }

    // 세션 제거 (로그아웃)
    public void expireSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
