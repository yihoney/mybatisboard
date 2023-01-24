package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Post;

import java.util.List;
import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public interface PostService {
     Post getPost(Long id);

    List<Post> getAllPosts_flagFalse();

    List<Post> getAllPosts_flagTrue();

    void insertPost(Post post);

    void updatePost(Post post);

    void deleteById(Long id);

    void restoreById(Long id);
}
