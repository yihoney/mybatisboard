package com.nhnacademy.jdbc.board.exception;

public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException() {
        super("비밀번호가 일치하지 않습니다!!");
    }
}
