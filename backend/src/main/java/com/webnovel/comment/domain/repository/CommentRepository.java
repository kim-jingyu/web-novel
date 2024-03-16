package com.webnovel.comment.domain.repository;

import com.webnovel.comment.domain.Comment;
import com.webnovel.comment.dto.CommentsResponseDto;
import com.webnovel.comment.exception.CommentNotFoundException;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment getById(Long commentId) {
        return findById(commentId)
            .orElseThrow(CommentNotFoundException::new);
    }

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true")) 
    @Query("select new com.webnovel.comment.dto.CommentsResponseDto(m.memberId, c.commentId, c.round.roundId, m.nickName, c.content)"
        + "from Comment c left join Member m on c.member.memberId = m.memberId and c.round.roundId =:roundId")
    Page<CommentsResponseDto> findAllByRound_RoundId(@Param("roundId") Long roundId, Pageable pageable);

    Page<Comment> findByRound_RoundId(Long roundId, Pageable pageable);
}