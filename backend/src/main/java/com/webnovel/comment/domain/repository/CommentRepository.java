package com.webnovel.comment.domain.repository;

import com.webnovel.comment.domain.Comment;
import com.webnovel.comment.exception.CommentNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment getById(Long commentId) {
        return findById(commentId)
            .orElseThrow(CommentNotFoundException::new);
    }
}
