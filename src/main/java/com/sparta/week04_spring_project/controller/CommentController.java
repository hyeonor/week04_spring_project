package com.sparta.week04_spring_project.controller;

import com.sparta.week04_spring_project.model.Comment;
import com.sparta.week04_spring_project.dto.CommentRequestDto;
import com.sparta.week04_spring_project.service.CommentService;
import com.sparta.week04_spring_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails);
    }

    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @PutMapping("/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }
}