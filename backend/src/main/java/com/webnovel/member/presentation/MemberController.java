package com.webnovel.member.presentation;

import com.webnovel.auth.Auth;
import com.webnovel.auth.Member;
import com.webnovel.login.domain.LoginMember;
import com.webnovel.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Member
    @DeleteMapping("/member")
    public ResponseEntity<Void> removeMember(@Auth LoginMember member) {
        memberService.removeMember(member.getMemberId());
        return ResponseEntity
                .noContent()
                .build();
    }
}
