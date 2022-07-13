package com.sparta.week04_spring_project.service;

import com.sparta.week04_spring_project.dto.SignupRequestDto;
import com.sparta.week04_spring_project.model.User;
import com.sparta.week04_spring_project.model.UserRoleEnum;
import com.sparta.week04_spring_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        Pattern patternPass = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }else if (!pattern.matcher(requestDto.getUsername()).matches()){//닉네임 정규표현식
            throw new IllegalArgumentException("최소 3자 이상, 영문 숫자만 가능합니다.");
        }else if (!patternPass.matcher(requestDto.getPassword()).matches()){
            throw new IllegalArgumentException("최소 4자 이상");
        }else if(requestDto.getPassword().contains(requestDto.getUsername())){//비밀번호에 아이디 포함 확인
            throw new IllegalArgumentException("비밀번호에 ID를 포함할 수 없습니다.");
        }else if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())){//비밀번호 불일치
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}