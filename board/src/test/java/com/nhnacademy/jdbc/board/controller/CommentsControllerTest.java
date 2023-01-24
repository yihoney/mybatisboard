package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.service.CommentsService;
import com.nhnacademy.jdbc.board.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentsControllerTest {

    CommentsService commentsService;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        commentsService = mock(CommentsService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CommentsController(commentsService))
                .build();
    }

    @Test
    void testPostRegisterComments() throws Exception {

        mockMvc.perform(get("/post?id=1"))
//                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}