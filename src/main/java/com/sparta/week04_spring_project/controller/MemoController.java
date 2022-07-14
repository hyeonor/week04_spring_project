package com.sparta.week04_spring_project.controller;

import com.sparta.week04_spring_project.model.Memo;
import com.sparta.week04_spring_project.repository.MemoRepository;
import com.sparta.week04_spring_project.dto.MemoRequestDto;
import com.sparta.week04_spring_project.security.UserDetailsImpl;
import com.sparta.week04_spring_project.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails.getUser().getId() == null) {
            throw new IllegalArgumentException("로그인 한 회원만 작성할 수 있습니다.");
        }
        Memo memo = new Memo(requestDto, userDetails.getUsername());
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);//하루전
        LocalDateTime end = LocalDateTime.now();//지금
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return "삭제 완료";
    }

    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }
}
