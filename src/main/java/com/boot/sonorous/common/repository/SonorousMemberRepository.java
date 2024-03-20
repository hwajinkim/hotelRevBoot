package com.boot.sonorous.common.repository;


import com.boot.sonorous.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SonorousMemberRepository extends JpaRepository<Member, Integer> {

    Member findBymId(String mId);

}
