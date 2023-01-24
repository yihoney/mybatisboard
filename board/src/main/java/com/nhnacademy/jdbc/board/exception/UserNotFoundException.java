package com.nhnacademy.jdbc.board.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("일치하는 사용자 정보가 없습니다!!");
    }
}
