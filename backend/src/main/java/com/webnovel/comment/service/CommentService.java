package com.webnovel.comment.service;

import org.springframework.stereotype.Service;

import com.webnovel.comment.domain.Comment;
import com.webnovel.comment.domain.repository.CommentRepository;

import com.webnovel.member.domain.Member;
import com.webnovel.round.domain.Round;
import com.webnovel.comment.dto.CommentCreateDto;
import com.webnovel.comment.dto.CommentDeleteDto;
import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.comment.exception.LengthUnderException;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.round.domain.repository.RoundRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final MemberRepository memberRepository;
    private final RoundRepository roundRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentCreateDto request) {
        if (request.getContent() == null || request.getContent().length() < 5) {
            throw new LengthUnderException();
        } else if (request.getContent().length() > 255) {
            throw new LengthOverException();
        }

        Member member = memberRepository.getById(request.getMemberId());
        Round round = roundRepository.getById(request.getRoundId());
        Comment comment = Comment.create(member, round, request.getContent());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(CommentDeleteDto request) {
        commentRepository.delete(
            commentRepository.getById(request.getCommentId())
        );
    }
}
