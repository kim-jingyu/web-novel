package com.webnovel.comment;

import com.webnovel.comment.domain.Comment;
import com.webnovel.comment.domain.repository.CommentRepository;
import com.webnovel.comment.dto.CommentCreateDto;
import com.webnovel.comment.dto.CommentDeleteDto;
import com.webnovel.comment.dto.CommentsResponseDto;
import com.webnovel.comment.exception.CommentNotFoundException;
import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.comment.exception.LengthUnderException;
import com.webnovel.comment.service.CommentService;
import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.Role;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.recommend.domain.repository.RecommendRepository;
import com.webnovel.round.domain.Round;
import com.webnovel.round.domain.repository.RoundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    RecommendRepository recommendRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("댓글 등록 성공")
    void caseCreate() { 
         // given
        Round round = Round.builder()
            .likes(0)
            .roundNum(1)
            .name("회차1")
            .content("내용")
            .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        CommentCreateDto request = CommentCreateDto.builder()
            .memberId(savedMember.getMemberId())
            .roundId(savedRound.getRoundId())
            .content("comment test body")
            .build();

        // when
        commentService.createComment(request);

        // then
        Comment findComment = commentRepository.findAll().get(0);
        assertThat(findComment.getContent()).isEqualTo("comment test body");
    }

    @Test
    @DisplayName("글자수 초과")
    void caseLengthOver() { 
        // given
        Round round = Round.builder()
        .likes(0)
        .roundNum(1)
        .name("회차1")
        .content("내용")
        .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        Random random = new Random();
        String randomString = random.ints(32, 127)
            .limit(516)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        CommentCreateDto request = CommentCreateDto.builder()
            .memberId(savedMember.getMemberId())
            .roundId(savedRound.getRoundId())
            .content(randomString)
            .build();

        // when
        assertThatThrownBy(() -> commentService.createComment(request))
            .isInstanceOf(LengthOverException.class);
    }

    @Test
    @DisplayName("글자수 부족")
    void caseLengthUnder() { 
        // given
        Round round = Round.builder()
        .likes(0)
        .roundNum(1)
        .name("회차1")
        .content("내용")
        .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        CommentCreateDto request = CommentCreateDto.builder()
            .memberId(savedMember.getMemberId())
            .roundId(savedRound.getRoundId())
            .content("test")
            .build();

        // when
        assertThatThrownBy(() -> commentService.createComment(request))
            .isInstanceOf(LengthUnderException.class);
    }

    @Test
    @DisplayName("본문 공백")
    void caseLengthZero() { 
        //given
        Round round = Round.builder()
        .likes(0)
        .roundNum(1)
        .name("회차1")
        .content("내용")
        .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        CommentCreateDto request = CommentCreateDto.builder()
            .memberId(savedMember.getMemberId())
            .roundId(savedRound.getRoundId())
            .content(null)
            .build();

        // when
        assertThatThrownBy(() -> commentService.createComment(request))
            .isInstanceOf(LengthUnderException.class);
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    void caseRemove() { 
         // given
         Round round = Round.builder()
            .likes(0)
            .roundNum(1)
            .name("회차1")
            .content("내용")
            .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        Comment comment = Comment.builder()
            .member(savedMember)
            .round(savedRound)
            .content("comment test body")
            .build();

        Comment savedComment = commentRepository.save(comment);

        // when
        CommentDeleteDto request = CommentDeleteDto.builder()
            .commentId(savedComment.getCommentId())
            .memberId(savedComment.getMember().getMemberId())
            .build();

        commentService.deleteComment(request);

        // then
        Optional<Comment> findComments = commentRepository.findById(savedComment.getCommentId());
        assertThat(findComments).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("이미 삭제된 댓글")
    void caseAlreadyRemoved() { 
         // given
         Round round = Round.builder()
            .likes(0)
            .roundNum(1)
            .name("회차1")
            .content("내용")
            .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember = memberRepository.save(member);

        Comment comment = Comment.builder()
            .member(savedMember)
            .round(savedRound)
            .content("comment test body")
            .build();

        Comment savedComment = commentRepository.save(comment);

        // when
        CommentDeleteDto request = CommentDeleteDto.builder()
            .commentId(savedComment.getCommentId())
            .memberId(savedComment.getMember().getMemberId())
            .build();

        commentService.deleteComment(request);

        // then
        assertThatThrownBy(() -> commentService.deleteComment(request))
            .isInstanceOf(CommentNotFoundException.class);
    }

    @Test
    @DisplayName("댓글 목록 가져오기")
    void caseGetAllComments() { 
         // given
        Round round = Round.builder()
            .likes(0)
            .roundNum(1)
            .name("회차1")
            .content("내용")
            .build();
        Round savedRound = roundRepository.save(round);

        Member member1 = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember1= memberRepository.save(member1);

        Member member2 = Member.builder()
        .email("user@email.com")
        .nickName("user2")
        .password("1234")
        .role(Role.USER)
        .money(1000)
        .build();
        Member savedMember2 = memberRepository.save(member2);

        CommentCreateDto request_1 = CommentCreateDto.builder()
            .memberId(savedMember1.getMemberId())
            .roundId(savedRound.getRoundId())
            .content("comment1")
            .build();
        commentService.createComment(request_1);

        CommentCreateDto request_2 = CommentCreateDto.builder()
        .memberId(savedMember2.getMemberId())
        .roundId(savedRound.getRoundId())
        .content("comment2")
        .build();
        commentService.createComment(request_2);

        CommentCreateDto request_3 = CommentCreateDto.builder()
        .memberId(savedMember1.getMemberId())
        .roundId(savedRound.getRoundId())
        .content("comment3")
        .build();
        commentService.createComment(request_3);

        // then
        List<CommentsResponseDto> comments = 
            commentRepository.findAllByRound_RoundId(savedRound.getRoundId());

        assertThat(comments).extracting("Name").containsExactly("user", "user2", "user");
    }

    @Test
    @DisplayName("댓글 페이지 가져오기")
    void caseGetCommentsPage() { 
         // given
        Round round = Round.builder()
            .likes(0)
            .roundNum(1)
            .name("회차1")
            .content("내용")
            .build();
        Round savedRound = roundRepository.save(round);

        Member member1 = Member.builder()
            .email("user@email.com")
            .nickName("user")
            .password("1234")
            .role(Role.USER)
            .money(1000)
            .build();
        Member savedMember1= memberRepository.save(member1);

        Member member2 = Member.builder()
        .email("user@email.com")
        .nickName("user2")
        .password("1234")
        .role(Role.USER)
        .money(1000)
        .build();
        Member savedMember2 = memberRepository.save(member2);

        CommentCreateDto request_1 = CommentCreateDto.builder()
            .memberId(savedMember1.getMemberId())
            .roundId(savedRound.getRoundId())
            .content("comment1")
            .build();
        commentService.createComment(request_1);

        CommentCreateDto request_2 = CommentCreateDto.builder()
        .memberId(savedMember2.getMemberId())
        .roundId(savedRound.getRoundId())
        .content("comment2")
        .build();
        commentService.createComment(request_2);

        CommentCreateDto request_3 = CommentCreateDto.builder()
        .memberId(savedMember1.getMemberId())
        .roundId(savedRound.getRoundId())
        .content("comment3")
        .build();
        commentService.createComment(request_3);

        // then
        Pageable pageable = PageRequest.of(0, 10);
        Page<CommentsResponseDto> comments = 
            commentRepository.findAllByRound_RoundId(savedRound.getRoundId(), pageable);

        for (CommentsResponseDto comment : comments) {
            System.out.println(comment.getName());
            System.out.println(comment.getContent());
        }

        assertThat(comments).extracting("Name").containsExactly("user", "user2", "user");
    }
}
