package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.*;
import com.nhnacademy.jdbc.board.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CommentsService commentsService;

    public PostController(PostService postService, CommentsService commentsService) {
        this.postService = postService;
        this.commentsService = commentsService;
    }

    @GetMapping(params = "!id") // 게시글 목록 조회
    public String getPostsView(Model model, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        List<Post> postList = postService.getAllPosts_flagFalse();
        commentsService.setCommentsListByPostId_flagFalse(postList);
        Collections.reverse(postList);
        model.addAttribute("postList", postList);
        model.addAttribute("loginUser", loginUser);
        return "postsView";
    }

    @GetMapping(params = "id")
    public String getPostDetailsView(@RequestParam Long id, @RequestParam(required = false) Long cmt_id,
                                     Model model, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        Post post = postService.getPost(id);
        commentsService.setCommentsByPostId_flagFalse(post);
        model.addAttribute("commentsForm", new CommentsRequest());
        model.addAttribute("post", post);
        model.addAttribute("loginUser", loginUser);
        if (cmt_id != null) {
            Comments comments = commentsService.getComments(cmt_id);
            if (loginUser.getId() == comments.getWriter().getId() || loginUser.getUsertype().equals("admin")) {
                model.addAttribute("editComments", comments);
            }
        }
        return "postDetailsView";
    }

    @GetMapping("/register")
    public String getPostRegisterForm(Model model) {
        model.addAttribute("postForm", new PostRequest());
        return "postRegisterForm";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("postForm") PostRequest request, BindingResult bindingResult,
                               @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        if (bindingResult.hasErrors()) {
            return "postRegisterForm";
        }
        Post post = new Post(null, request.getTitle(), request.getContent(), null, null,
                null, false, loginUser, null, null);
        postService.insertPost(post);
        return "redirect:/post";
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam Long id, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        Post post = postService.getPost(id);

        if (loginUser.getId() == post.getWriter().getId() || loginUser.getUsertype().equals("admin")) {
            postService.deleteById(id);
            return "redirect:/post";
        }
        return "redirect:/post?id="+id;
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam Long id, @SessionAttribute(value = "loginUser", required = false) User loginUser,
                           Model model) {

        Post post = postService.getPost(id);

        if (loginUser.getId() == post.getWriter().getId() || loginUser.getUsertype().equals("admin")) {
            model.addAttribute("postForm", new PostRequest(post.getTitle(), post.getContent()));
            model.addAttribute("post", post);
            return "postEditForm";
        }

        return "redirect:/post?id="+id;
    }

    @PostMapping("/edit")
    public String EditPost(@RequestParam Long id, Model model,
                           @Valid @ModelAttribute("postForm") PostRequest request, BindingResult bindingResult,
                           @SessionAttribute(value = "loginUser", required = false) User loginUser) {

        Post post = postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "postEditForm";
        }
        Post newPost = new Post(post.getId(), request.getTitle(), request.getContent(), post.getCreated_at(), null,
                post.getParent_id(), post.is_deleted(), post.getWriter(), loginUser, post.getCommentsList());
        postService.updatePost(newPost);
        return "redirect:/post?id="+id;
    }

    @GetMapping(value = "/restoration", params = "!id") // 게시글 목록 조회(삭제글)
    public String getDeletedPostsView(Model model, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        if (!loginUser.getUsertype().equals("admin")) {
            return "redirect:/post";
        }
        List<Post> postList = postService.getAllPosts_flagTrue();
        commentsService.setCommentsListByPostId_flagFalse(postList);
        Collections.reverse(postList);
        model.addAttribute("postList", postList);
        model.addAttribute("loginUser", loginUser);
        return "deletedPostsView";
    }

    @GetMapping(value = "/restoration", params = "id") // 게시글 복구
    public String restorePost(@RequestParam Long id, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        if (loginUser.getUsertype().equals("admin")) {
            postService.restoreById(id);
        }
        return "redirect:/post";
    }
}
