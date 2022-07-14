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
        memo.update(requestDto);

        return "업데이트 완료";
    }
}
