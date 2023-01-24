package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comments {
    private Long id;
    private Long post_id;
    private User writer;
    private User modifier;
    private String content;
    private Date created_at;
    private Date modified_at;
    private Long parent_id;
    private boolean is_deleted;
}
