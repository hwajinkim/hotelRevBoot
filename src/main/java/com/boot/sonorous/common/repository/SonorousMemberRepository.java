package com.boot.sonorous.common.repository;


import com.boot.sonorous.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SonorousMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);


    Member findByUsernameAndPassword(String username, String password);
}
