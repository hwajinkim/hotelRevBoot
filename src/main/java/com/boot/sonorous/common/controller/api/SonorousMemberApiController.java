package com.boot.sonorous.common.controller.api;

import com.boot.sonorous.common.dto.MemberDto;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.service.SonorousMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SonorousMemberApiController {

    @Autowired
    private SonorousMemberService sonorousMemberService;

    @GetMapping("/memberInfo")
    public ResponseEntity<MemberDto> memberInfo(@AuthenticationPrincipal UserDetails userDetails){
        Optional<Member> optionalMember = sonorousMemberService.getMember(userDetails.getUsername());
        MemberDto memberDto = new MemberDto();
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            memberDto.setUsername(member.getUsername());
            memberDto.setEmail(member.getEmail());
            memberDto.setPhone(member.getPhone());
        }
        return ResponseEntity.ok(memberDto);
    }
}
