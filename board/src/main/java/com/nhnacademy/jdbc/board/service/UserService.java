package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public interface UserService {
     Optional<User> getUserById(Long id);
     Optional<User> getUserByUsername(String username);
     List<User> getAllUsers();

     void insertUser(User user);

     void updatePasswordById(String password, Long id);

     void deleteById(Long id);

     void doLogin(String username, String password);
}
