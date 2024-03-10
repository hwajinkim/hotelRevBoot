package com.boot.sonorous.user.repository;


import com.boot.sonorous.admin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SonorousMemberRepository extends JpaRepository<Member, Integer> {

    Member findBymId(String mId);

}
