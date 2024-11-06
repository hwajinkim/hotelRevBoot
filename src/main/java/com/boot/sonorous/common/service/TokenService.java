package com.boot.sonorous.common.service;

import com.boot.sonorous.common.entity.RefreshToken;
import com.boot.sonorous.common.repository.RefreshTokenRepository;
import com.boot.sonorous.common.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenService(RefreshTokenRepository refreshTokenRepository, JwtTokenProvider jwtTokenProvider){
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //Refresh Token 저장
    public void saveRefreshToken(String userId, String token){
        RefreshToken refreshToken = new RefreshToken(userId, token);
        refreshTokenRepository.save(refreshToken);
    }

    //Refresh Token 조회
    public String getRefreshToken(String userId){
        return refreshTokenRepository.findById(userId)
                .map(RefreshToken::getToken)
                .orElse(null);
    }

    //Refresh Token 삭제
    public void deleteRefreshToken(String refreshToken){
        String userId = jwtTokenProvider.getAuthentication(refreshToken).getName();
        refreshTokenRepository.deleteById(userId);
    }

}
