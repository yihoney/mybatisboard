package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.service.CommentsService;
import com.nhnacademy.jdbc.board.service.PostService;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest{

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController())
                .build();
    }

    @Test
    void testGetRootPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }
}