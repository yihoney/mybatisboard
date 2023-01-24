package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.*;
import com.nhnacademy.jdbc.board.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/post")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
//        this.userService = userService;
        this.commentsService = commentsService;
    }

    @PostMapping(params = "id")
    public String doRegisterComments(@RequestParam Long id, @RequestParam(required = false) Long cmt_id,
                                     @Valid @ModelAttribute("commentsForm") CommentsRequest request, BindingResult bindingResult,
                                     @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        if (loginUser == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/post?id=" + id;
        }

        if(cmt_id!=null) {
            Comments comments = commentsService.getComments(cmt_id);
            Comments newComments = new Comments(comments.getId(), comments.getPost_id(), comments.getWriter(), loginUser,
                    request.getContent(), comments.getCreated_at(), null, comments.getParent_id(), comments.is_deleted());
            commentsService.updateComments(newComments);
            return "redirect:/post?id=" + id;
        }

        Comments comments = new Comments(null, id, loginUser, null, request.getContent(),
                null, null, null, false);
        commentsService.insertComments(comments);
        return "redirect:/post?id=" + id;
    }

    @GetMapping("/comments/delete")
    public String deleteComments(@RequestParam Long id, @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        Comments comments = commentsService.getComments(id);
        Long post_id = comments.getPost_id();
        if (loginUser.getId() == comments.getWriter().getId() || loginUser.getUsertype().equals("admin")) {
            commentsService.deleteById(id);
        }
        return "redirect:/post?id=" + post_id;
    }


//    @PostMapping(params = "id")
//    public String editComments(@RequestParam Long id, @RequestParam(required = false) Long cmt_id,
//                               @Valid @ModelAttribute("commentsForm") CommentsRequest request, BindingResult bindingResult,
//                               @SessionAttribute(value = "loginUser", required = false) User loginUser) {
//        if (loginUser == null) {
//            return "redirect:/login";
//        }
//        if (bindingResult.hasErrors()) {
//            return "redirect:/post?id=" + id;
//        }
//        Comments comments = commentsService.getComments(cmt_id);
//        Comments newComments = new Comments(comments.getId(), comments.getPost_id(), comments.getWriter(), loginUser,
//                request.getContent(), comments.getCreated_at(), null, comments.getParent_id(), comments.is_deleted());
//        commentsService.updateComments(newComments);
//        return "redirect:/post?id=" + id;
//    }

}
