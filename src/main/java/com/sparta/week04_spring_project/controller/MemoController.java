package com.sparta.week04_spring_project.controller;

import com.sparta.week04_spring_project.model.Memo;
import com.sparta.week04_spring_project.repository.MemoRepository;
import com.sparta.week04_spring_project.dto.MemoRequestDto;
import com.sparta.week04_spring_project.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);//하루전
        LocalDateTime end = LocalDateTime.now();//지금
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
        //return memoRepository.findAllByOrderByModifiedAtDesc();
    }

//    //기존
//    @DeleteMapping("/api/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        memoRepository.deleteById(id);
//        return id;
//    }

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).get();
        if(memo.getPassword().equals(requestDto.getPassword())) {
            memoRepository.deleteById(id);
            return "삭제 완료";
        }
        else {
            return "비밀번호 불일치";
        }
    }

    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        String result;
        result = memoService.update(id, requestDto);
        return result;
    }
}
