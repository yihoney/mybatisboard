package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsRequest {

    @NotEmpty(message = "댓글 내용은 비어있을 수 없습니다.")
    @Length(min=1, max=10000, message = "댓글의 길이는 1자 - 10000자 사이이어야 합니다.")
    String content;

}
