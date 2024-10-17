package com.boot.sonorous.common.controller;

import com.boot.sonorous.admin.dto.ResponseDto;
import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.service.SonorousMemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.util.HashMap;
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
    public ResponseEntity<ResponseDto<String>> memberExists(@ModelAttribute Member member){

        Optional<Member> memberDetail = sonorousMemberService.getMember(member.getUsername());

        ResponseDto<String> dto = new ResponseDto<>();
        dto.setStatus(HttpStatus.OK.value());
        if(memberDetail.isEmpty()){
            dto.setData("true");
        }else{
            dto.setData("false");
        }
        return ResponseEntity.ok(dto);
    }



    @GetMapping("/common/loginForm")
    public String loginForm(){
        return "common/loginForm";
    }

    @PostMapping("/common/login")
    public String login(@ModelAttribute Member member,
                        Model model,
                        HttpSession session) throws Exception{

        String message = "";
        boolean isError = true;

        String viewPage = "common/message";

        String username = member.getUsername();
        String password = member.getPassword();
        Member loginMember = sonorousMemberService.getLogin(username, password);
        if(loginMember != null){
            session.setAttribute("loginMember", loginMember);
            message = member.getUsername()+" 님 환영합니다.";
            isError = false;
        } else {
            message = "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.";
            isError = true;
        }

        model.addAttribute("isError", isError);
        model.addAttribute("message", message);
        model.addAttribute("locationURL", "/main");

        return viewPage;
    }

    @GetMapping("/common/logout")
    public String logout(Model model,
                         HttpSession session, HttpServletResponse response) throws Exception{

        session.removeAttribute("loginMember");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('로그아웃 되었습니다.'); location.href='/main';</script>");
        out.flush();
        out.close();

        return null;

    }

}