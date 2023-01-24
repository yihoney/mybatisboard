package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Comments;
import com.nhnacademy.jdbc.board.domain.Post;

import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public interface CommentsService {
    Comments getComments(Long id);

    void setCommentsListByPostId_flagFalse(List<Post> postList);

    void setCommentsByPostId_flagFalse(Post post);

    void insertComments(Comments comments);

    void deleteById(Long id);

    void updateComments(Comments comments);
}
