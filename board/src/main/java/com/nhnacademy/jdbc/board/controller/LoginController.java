package com.nhnacademy.jdbc.board.controller;


import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.exception.PasswordIncorrectException;
import com.nhnacademy.jdbc.board.exception.UserNotFoundException;
import com.nhnacademy.jdbc.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginForm(@CookieValue(value = "SESSION", required = false) String session,
                        @SessionAttribute(value = "loginUser", required = false) User loginUser) {

        if (Optional.ofNullable(session).isPresent() && loginUser != null) {
            return "redirect:/post";
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String username, @RequestParam("pwd") String pwd,
                          HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            userService.doLogin(username, pwd);
        } catch (UserNotFoundException e) {
            return handleLoginException(e.getMessage(), model);
        } catch (PasswordIncorrectException e) {
            return handleLoginException(e.getMessage(), model);
        }

        HttpSession session = request.getSession(true);
        User loginUser = userService.getUserByUsername(username).get();
        session.setAttribute("loginUser", loginUser);
        Cookie cookie = new Cookie("SESSION", session.getId());
        response.addCookie(cookie);
        return "redirect:/post";
    }

    private String handleLoginException(String e, Model model) {
        log.info(e);
        model.addAttribute("exceptionMessage", e);
        return "loginForm";
    }
}

