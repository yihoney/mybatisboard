package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private String title;
    private String content;
    private Date created_at;
    private Date modified_at;
    private Long parent_id;
    private boolean is_deleted;
    private User writer;
    private User modifier;
    private List<Comments> commentsList;

}
