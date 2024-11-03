package com.boot.sonorous.common.controller;

import com.boot.sonorous.admin.dto.ResponseDto;
import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.common.dto.SignUpDto;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.service.SonorousMemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SonorousLoginController {

    @Autowired
    private SonorousMemberService sonorousMemberService;

    @GetMapping("/common/joinForm")
    public String joinForm(){
        return "common/joinForm";
    }

    @PostMapping("/common/memberExists")
    public ResponseEntity<Map<String, Object>> memberExists(@RequestBody Map<String, String> params){

        String username = params.get("username");

        Optional<Member> memberDetail = sonorousMemberService.getMember(username);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("data", memberDetail.isEmpty() ? "true" : "false");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/common/join")
    public ResponseEntity<Integer> join(@RequestBody SignUpDto signUpDto) {

        sonorousMemberService.insert(signUpDto);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/common/loginForm")
    public String loginForm(){
        return "common/loginForm";
    }
}