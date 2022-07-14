package com.sparta.week04_spring_project.service;

import com.sparta.week04_spring_project.model.Comment;
import com.sparta.week04_spring_project.dto.CommentRequestDto;
import com.sparta.week04_spring_project.repository.CommentRepository;
import com.sparta.week04_spring_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        return comment.update(requestDto);
    }

    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto, userDetails.getUser().getUsername());
        return commentRepository.save(comment);
    }
}
