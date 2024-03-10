package com.boot.sonorous.user.service;

import com.boot.sonorous.admin.entity.Member;
import com.boot.sonorous.user.repository.SonorousMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SonorousMemberService {

    @Autowired
    SonorousMemberRepository sonorousMemberRepository;

    public Member getLogin(String mId){
       Member member = sonorousMemberRepository.findBymId(mId);
       return member;
    }
}
