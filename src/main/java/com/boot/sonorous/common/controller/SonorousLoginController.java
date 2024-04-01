package com.boot.sonorous.common.controller;

import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.common.service.SonorousMemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.PrintWriter;

@Controller
public class SonorousLoginController {

    @Autowired
    private SonorousMemberService sonorousMemberService;

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

        String mId = member.getMId();
        Member loginMember = sonorousMemberService.getLogin(mId);
        if(loginMember != null && member.getMPw().equals(loginMember.getMPw())){
            session.setAttribute("loginMember", loginMember);
            message = member.getMId()+" 님 환영합니다.";
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
