package com.boot.sonorous.admin.controller;

import com.boot.sonorous.admin.service.SonorousRoomService;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.admin.entity.Room;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.PrintWriter;
import java.util.List;

@Controller
public class SonorousRoomController {

    @Autowired
    private SonorousRoomService sonorousRoomService;

    @GetMapping("/roomList")
    public String roomList(Model model, HttpSession session, HttpServletResponse response) throws Exception {
        // 1. 세션값이 널이거나
        // 2. 관리자 권한이 아니면 로그인 페이지 이동
        Member loginMember = (Member)session.getAttribute("loginMember");
        if(loginMember == null || !(loginMember.getMAuth().equals("ADMIN"))){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('접근 권한이 없습니다.');</script>");
            out.flush();
            out.close();
        }

        List<Room> roomList = sonorousRoomService.roomList();
        System.out.print(roomList);

        model.addAttribute("roomList", roomList);


        return "admin/roomList";
    }
}
