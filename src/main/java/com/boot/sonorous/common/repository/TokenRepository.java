package com.boot.sonorous.common.repository;

import com.boot.sonorous.common.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, String> {

    boolean existsByRefreshToken(String token);
}
