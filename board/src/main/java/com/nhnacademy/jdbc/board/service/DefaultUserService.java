package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.exception.PasswordIncorrectException;
import com.nhnacademy.jdbc.board.exception.UserNotFoundException;
import com.nhnacademy.jdbc.board.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */

@Service
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUserById(Long id) {

        return userMapper.getUserById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getUsers();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updatePasswordById(String password, Long id) {
        userMapper.updatePasswordById(password, id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void doLogin(String username, String password) {
        Optional<User> optionalUser = getUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (!optionalUser.get().getPassword().equals(password)) {
            throw new PasswordIncorrectException();
        }
    }
}
