package com.boot.sonorous.common.repository;

import com.boot.sonorous.common.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    /*Optional<Token> findByKey(String key);*/
}
