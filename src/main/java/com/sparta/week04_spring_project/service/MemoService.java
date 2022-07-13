package com.sparta.week04_spring_project.service;

import com.sparta.week04_spring_project.model.Memo;
import com.sparta.week04_spring_project.repository.MemoRepository;
import com.sparta.week04_spring_project.dto.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public String update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        //비밀번호 확인
        if (memo.getPassword().equals(requestDto.getPassword())) {
            //memo.getPassword() <- DB 데이터 가져온것
            //requestDto.getPassword() <- 확인을 위해 입력한 비밀번호
            memo.update(requestDto);
            return "업데이트 완료";
        }
        else {
            return "비밀번호 불일치";
        }
    }
}
