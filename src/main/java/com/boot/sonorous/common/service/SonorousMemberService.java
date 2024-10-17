package com.boot.sonorous.common.service;

import com.boot.sonorous.common.entity.JwtToken;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.repository.SonorousMemberRepository;
import com.boot.sonorous.common.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SonorousMemberService {

    @Autowired
    SonorousMemberRepository sonorousMemberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public Optional<Member> getMember(String username){
        Optional<Member> member = sonorousMemberRepository.findByUsername(username);
        return member;
    }


    // 세션 기반 인증
    public Member getLogin(String username, String password){
        Member member = sonorousMemberRepository.findByUsernameAndPassword(username, password);
        return member;
    }

    // 토큰 기반 인증
    @Transactional
    public JwtToken signIn(String username, String password){
        // 1. username + password 를 기반으로 Authentication 겍체 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증, authenticate() 메서드를 통해 요청된 Member에 대한 검증 진행
        Authentication authentication
                = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return  jwtToken;
    }


    public void insert(Member member) {

        member.get

        sonorousMemberRepository.save(member);
    }
}