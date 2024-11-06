package com.boot.sonorous.common.service;

import com.boot.sonorous.common.entity.RefreshToken;
import com.boot.sonorous.common.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //Refresh Token 저장
    public void saveRefreshToken(String userId, String token){
        RefreshToken refreshToken = new RefreshToken(userId, token);
        refreshTokenRepository.save(refreshToken);
    }
}
