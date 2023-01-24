package com.nhnacademy.jdbc.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    public LogoutController() {
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        session.removeAttribute("loginUser");
        Cookie cookieSession = new Cookie("SESSION", null);
        cookieSession.setMaxAge(0);
        response.addCookie(cookieSession);
        session.invalidate();
        return "redirect:/post";
    }
}
