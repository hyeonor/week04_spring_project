package com.sparta.week04_spring_project.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private Long blogId;
}
