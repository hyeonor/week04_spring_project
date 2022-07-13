package com.sparta.week04_spring_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
