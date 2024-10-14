package com.boot.sonorous.admin.controller;

import com.boot.sonorous.admin.entity.RoomImage;
import com.boot.sonorous.admin.service.SonorousRoomService;
import com.boot.sonorous.common.entity.BaseTimeEntity;
import com.boot.sonorous.common.entity.Member;
import com.boot.sonorous.admin.entity.Room;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.List;

@Controller
public class SonorousRoomController {

    @Autowired
    private SonorousRoomService sonorousRoomService;

    @GetMapping("/admin/roomList")
    public String roomList() {
        return "admin/roomList";
    }

    @GetMapping("/admin/roomInsertPage")
    public String roomInsertPage() throws Exception {
        return "admin/roomInsert";
    }

    @PostMapping("/admin/roomInsert")
    public String roomInsert(Room room,
                             List<MultipartFile> files,
                             MultipartFile thumbnail,
                             Model model) throws Exception {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = "";
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }
        sonorousRoomService.insert(room, files, thumbnail, userName);

        model.addAttribute("message", "방 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/admin/roomList");

        return "common/writeMessage";
    }

    @GetMapping("/admin/roomView")
    public String roomView(Model model, Room room){

        model.addAttribute("roomId", room.getId());
        return "admin/roomView";
    }

    @GetMapping("/admin/roomUpdatePage")
    public String roomUpdatePage(Model model, Room room){
        Room roomDetail = sonorousRoomService.roomView(room.getId());
        model.addAttribute("room", roomDetail);
        return "admin/roomUpdate";
    }

    @PostMapping("/admin/roomUpdate/{id}")
    public String roomUpdate(@PathVariable("id") Integer id, Room room, RoomImage roomImage, MultipartFile file, Model model) throws Exception {

        sonorousRoomService.update(id, room, roomImage);

        model.addAttribute("message", "방 수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/admin/roomView?Id="+id);

        return "common/writeMessage";
    }

    @PostMapping("/admin/roomDelete/{id}")
    public String roomDelete(@PathVariable("id") Integer id, Model model){

        sonorousRoomService.delete(id);

        model.addAttribute("message", "방 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl","/admin/roomList");

        return "common/writeMessage";
    }

}
