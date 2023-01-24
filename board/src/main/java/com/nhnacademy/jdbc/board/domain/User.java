package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String usertype;
    private String username;
    private String password;
    private Date createdAt;

}
