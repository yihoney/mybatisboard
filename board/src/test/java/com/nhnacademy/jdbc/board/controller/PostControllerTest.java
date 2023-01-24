package com.nhnacademy.jdbc.board.controller;


import com.nhnacademy.jdbc.board.service.CommentsService;
import com.nhnacademy.jdbc.board.service.PostService;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    PostService postService;
    CommentsService commentsService;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        postService = mock(PostService.class);
        commentsService = mock(CommentsService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PostController(postService, commentsService))
                .build();
    }

    @Test
    void testGetPostsView() throws Exception {
        mockMvc.perform(get("/post"))
                .andExpect(status().isOk())
                .andExpect(view().name("postsView"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testGetPostDetailsView() throws Exception {
        mockMvc.perform(get("/post?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("postDetailsView"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testGetPostRegisterForm() throws Exception {
        mockMvc.perform(get("/post/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("postRegisterForm"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testPostRegisterPost() throws Exception {

        mockMvc.perform(get("/post/register"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}