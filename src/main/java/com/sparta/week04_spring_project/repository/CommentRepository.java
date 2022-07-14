package com.sparta.week04_spring_project.repository;

import com.sparta.week04_spring_project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogIdOrderByCreatedAtDesc(Long blogId);
}
