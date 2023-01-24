package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Post;
import com.nhnacademy.jdbc.board.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */

@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public Post getPost(Long id){
        return postMapper.getPost(id);
    }

    @Override
    public List<Post> getAllPosts_flagFalse() {
        return postMapper.getPosts()
                .stream().filter(post -> post.is_deleted() == false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllPosts_flagTrue() {
        return postMapper.getPosts()
                .stream().filter(post -> post.is_deleted() == true)
                .collect(Collectors.toList());
    }

    @Override
    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void deleteById(Long id) {
        postMapper.updateDeleteFlagTrueById(id);
    }
    @Override
    public void restoreById(Long id) {
        postMapper.updateDeleteFlagFalseById(id);
    }
}
