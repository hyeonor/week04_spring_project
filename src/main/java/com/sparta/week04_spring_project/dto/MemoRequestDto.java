package com.sparta.week04_spring_project.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String title;
    private String username;
    private String password;
    private String contents;
}
