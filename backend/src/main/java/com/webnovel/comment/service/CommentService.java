package com.webnovel.comment.service;

import com.webnovel.comment.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webnovel.comment.domain.Comment;
import com.webnovel.comment.domain.repository.CommentRepository;

import com.webnovel.member.domain.Member;
import com.webnovel.round.domain.Round;
import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.comment.exception.LengthUnderException;
import com.webnovel.comment.exception.PageOffsetException;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.round.domain.repository.RoundRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private MemberRepository memberRepository;
    private RoundRepository roundRepository;
    private CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(CommentCreateDto request) {
        if (request.getContent() == null || request.getContent().length() < 5) {
            throw new LengthUnderException();
        } else if (request.getContent().length() > 255) {
            throw new LengthOverException();
        }

        Member member = memberRepository.getById(request.getMemberId());
        Round round = roundRepository.getById(request.getRoundId());
        Comment comment = Comment.create(member, round, request.getContent());
        commentRepository.save(comment);

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .build();
    }

    @Transactional
    public void deleteComment(CommentDeleteDto request) {
        commentRepository.delete(
            commentRepository.getById(request.getCommentId())
        );
    }

    public Page<CommentsResponseDto> getCommentsPageWithName(CommentsRequestDto request, Pageable pageable) {
        if (pageable.getOffset() < 0)
            throw new PageOffsetException();

        return commentRepository.findAllByRound_RoundId(request.getRoundId(), pageable);
    }

}
