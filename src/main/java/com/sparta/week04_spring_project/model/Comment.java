package com.sparta.week04_spring_project.model;

import com.sparta.week04_spring_project.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private Long blogId;

    public Comment(CommentRequestDto requestDto, String username) {
        this.contents = requestDto.getContents();
        this.blogId = requestDto.getBlogId();
        this.username = username;
    }

    public long update (CommentRequestDto requestDto){
        this.contents = requestDto.getContents();
        this.blogId = requestDto.getBlogId();
        return commentId;
    }
}