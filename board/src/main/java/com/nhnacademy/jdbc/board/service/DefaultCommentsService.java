package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Comments;
import com.nhnacademy.jdbc.board.domain.Post;
import com.nhnacademy.jdbc.board.mapper.CommentsMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */

@Service
public class DefaultCommentsService implements CommentsService {
    private final CommentsMapper commentsMapper;

    public DefaultCommentsService(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public Comments getComments(Long id) {
        return commentsMapper.getComments(id);
    }

    @Override
    public void setCommentsListByPostId_flagFalse(List<Post> postList) {
        for (Post post : postList) {
            List<Comments> commentsList =
                    commentsMapper.getAllCommentsByPostId(post.getId())
                            .stream().filter(comments -> comments.is_deleted() == false)
                            .collect(Collectors.toList());
            Collections.reverse(commentsList);
            post.setCommentsList(commentsList);
        }
    }

    @Override
    public void setCommentsByPostId_flagFalse(Post post) {
        List<Comments> commentsList =
                commentsMapper.getAllCommentsByPostId(post.getId())
                        .stream().filter(comments -> comments.is_deleted() == false)
                        .collect(Collectors.toList());
        Collections.reverse(commentsList);
        post.setCommentsList(commentsList);
    }

    @Override
    public void insertComments(Comments comments) {
        commentsMapper.insertComments(comments);
    }

    @Override
    public void deleteById(Long id) {
        commentsMapper.updateDeleteFlagById(id);
    }

    @Override
    public void updateComments(Comments comments) {
        commentsMapper.updateComments(comments);
    }
}
