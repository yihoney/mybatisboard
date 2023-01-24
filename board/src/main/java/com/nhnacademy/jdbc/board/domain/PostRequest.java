package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @NotEmpty(message = "제목은 비어있을 수 없습니다.")
    @Length(min=5, max=50, message = "제목의 길이는 5자 - 50자 사이어야 합니다.")
    String title;

    @NotEmpty(message = "본문 내용은 비어있을 수 없습니다.")
    @Length(min=5, max=20000, message = "본문 내용의 길이는 5자 - 20000자 사이이어야 합니다.")
    String content;

}
