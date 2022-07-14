package com.sparta.week04_spring_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private String title;
    private String password;
    private String contents;
}
