package com.boot.sonorous.common.service;

import com.boot.sonorous.common.dto.SignUpDto;
import com.boot.sonorous.common.entity.JwtToken;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.entity.RefreshToken;
import com.boot.sonorous.common.repository.SonorousMemberRepository;
import com.boot.sonorous.common.repository.TokenRepository;
import com.boot.sonorous.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SonorousMemberService {

    @Autowired
    SonorousMemberRepository sonorousMemberRepository;
    @Autowired
    TokenRepository tokenRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Optional<Member> getMember(String username){
        Optional<Member> member = sonorousMemberRepository.findByUsername(username);
        return member;
    }

    // 토큰 기반 인증
    @Transactional
    public JwtToken signIn(String username, String password){

        Member member = sonorousMemberRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(!encoder.matches(password, member.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        // 1. username + password 를 기반으로 Authentication 겍체 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, member.getPassword());

        // 2. 실제 검증, authenticate() 메서드를 통해 요청된 Member에 대한 검증 진행
        Authentication authentication
                = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return  jwtToken;
    }

    @Transactional
    public void insert(SignUpDto signUpDto) {

        String rawPassword = signUpDto.getPassword();
        String encPassword = encoder.encode(rawPassword);
        signUpDto.setPassword(encPassword);

        Member member = Member.builder()
                        .username(signUpDto.getUsername())
                        .password(signUpDto.getPassword())
                        .roles(signUpDto.getRoles())
                        .country(signUpDto.getCountry())
                        .eName(signUpDto.getEname())
                        .birth(signUpDto.getBirth())
                        .phone(signUpDto.getPhone())
                        .email(signUpDto.getEmail())
                        .build();

        sonorousMemberRepository.save(member);
    }
    public void insertRefreshToken(String refreshToken) {
        tokenRepository.save(new RefreshToken(refreshToken));
    }
    public boolean isTokenExpired(String token) {
        return jwtTokenProvider.validateToken(token);
    }


    public JwtToken reissueToken(String refreshToken) {
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

    public void deleteRefreshToken(String refreshToken) {
        if(tokenRepository.existsByRefreshToken(refreshToken)){
            tokenRepository.deleteById(refreshToken);
        }
    }


}