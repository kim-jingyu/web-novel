package com.webnovel.round.service;

import com.webnovel.comment.service.CommentService;
import com.webnovel.round.domain.Round;
import com.webnovel.round.domain.repository.RoundRepository;
import com.webnovel.round.dto.RoundInquiryDto;
import com.webnovel.round.dto.RoundResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoundService {
    private final RoundRepository roundRepository;
    private final CommentService commentService;

    public RoundResponseDto getRound(RoundInquiryDto request) {
        Round round = roundRepository.getById(request.getRoundId());
        return RoundResponseDto.of(round);
    }
}
