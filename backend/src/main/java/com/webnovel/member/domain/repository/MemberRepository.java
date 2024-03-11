package com.webnovel.member.domain.repository;

import com.webnovel.member.domain.Member;
import com.webnovel.member.exception.MemberNonFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    default Member getById(Long memberId) {
        return findById(memberId)
                .orElseThrow(MemberNonFoundException::new);
    }
}
