package com.nhnacademy.jdbc.board.interceptor;

import com.nhnacademy.jdbc.board.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if(session==null) {
            response.sendRedirect("/login");
            return false;
        }
        Optional<Object> loginUser = Optional.ofNullable(session.getAttribute("loginUser"));
        if (loginUser.isEmpty()) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
