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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String roomList(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        //List<RoomImage> roomList = sonorousRoomService.roomList();
        Page<RoomImage> list = null;
        list = sonorousRoomService.roomList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("roomList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/roomList";
    }

    @GetMapping("/admin/roomInsertPage")
    public String roomInsertPage() throws Exception {
        return "admin/roomInsert";
    }

    @PostMapping("/admin/roomInsert")
    public String roomInsert(Room room, RoomImage roomImage, MultipartFile file, Model model) throws Exception {

        roomImage.setRoom(room);
        sonorousRoomService.insert(roomImage, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/admin/roomList");

        return "common/writeMessage";
    }

    @GetMapping("/admin/roomView")
    public String roomView(Model model, Room room){
        RoomImage roomImage = sonorousRoomService.roomView(room.getId());
        model.addAttribute("room", roomImage);
        return "admin/roomView";
    }

    @GetMapping("/admin/roomUpdatePage")
    public String roomUpdatePage(Model model, Room room){
        RoomImage roomImage = sonorousRoomService.roomView(room.getId());
        model.addAttribute("room", roomImage);
        return "admin/roomUpdate";
    }

    @PostMapping("/admin/roomUpdate/{id}")
    public String roomUpdate(@PathVariable("id") Integer id, Room room, RoomImage roomImage, MultipartFile file, Model model) throws Exception {

        RoomImage roomTemp = sonorousRoomService.roomView(id);
        room.setRoomName(room.getRoomName());
        room.setRoomPrice(room.getRoomPrice());
        room.setRoomAmount(room.getRoomAmount());
        room.setRoomSize(room.getRoomSize());
        room.setPeopleNum(room.getPeopleNum());
        room.setBedType(room.getBedType());
        roomTemp.setRoom(room);
        sonorousRoomService.update(roomTemp);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/admin/roomView?Id="+id);

        return "common/writeMessage";
    }
}
