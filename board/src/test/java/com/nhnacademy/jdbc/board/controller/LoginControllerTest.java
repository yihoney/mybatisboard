package com.nhnacademy.jdbc.board.controller;


import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.exception.PasswordIncorrectException;
import com.nhnacademy.jdbc.board.exception.UserNotFoundException;
import com.nhnacademy.jdbc.board.service.CommentsService;
import com.nhnacademy.jdbc.board.service.PostService;
import com.nhnacademy.jdbc.board.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginControllerTest{

    UserService userService;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        userService = mock(UserService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService))
                .build();
    }

    @Test
    void getLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("loginForm"));
    }

    @Test
    void getLoginForm_loginAlready() throws Exception {
        Cookie cookie = new Cookie("SESSION", "test");
        User loginUser = Mockito.mock(User.class);

        mockMvc.perform(get("/login")
                        .sessionAttr("loginUser", loginUser)
                        .cookie(cookie))
                .andExpect(view().name("redirect:/post"));
    }

    @Test
    void doLogin_success() throws Exception {
        String id = "admin1";
        String pw = "adminadmin1";
        Optional<User> user = Optional.ofNullable(mock(User.class));
        Mockito.when(userService.getUserByUsername(id)).thenReturn(user);

        Assertions.assertDoesNotThrow(() -> userService.doLogin(id, pw));
        mockMvc.perform(post("/login")
                        .param("id",id)
                        .param("pwd",pw))
                .andExpect(view().name("redirect:/post"))
                .andExpect(cookie().exists("SESSION"));
    }

//    @Test
//    void doLogin_fail_incorrectidandpw() throws Exception {
//        String id = "admin";
//        String pw = "admin";
////        Optional<User> user = Optional.ofNullable(mock(null));
//
//        Mockito.when(userService.getUserByUsername(id)).thenThrow(UserNotFoundException.class);
////        Mockito.when(userService.getUserByUsername(id2).get()).thenReturn(user);
//
////        Mockito.verify()
//
//        Assertions.assertAll(
//                () ->  assertThrows(UserNotFoundException.class, () -> userService.doLogin(id, pw))
//        );
//
//
//
//        mockMvc.perform(post("/login")
//                        .param("id",id)
//                        .param("pwd",pw))
//                .andExpect(view().name("redirect:/login"));
//    }

}