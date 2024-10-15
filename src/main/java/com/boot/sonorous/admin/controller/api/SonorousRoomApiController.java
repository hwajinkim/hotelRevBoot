package com.boot.sonorous.admin.controller.api;

import com.boot.sonorous.admin.dto.ResponseDto;
import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.admin.entity.RoomImage;
import com.boot.sonorous.admin.service.SonorousRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class SonorousRoomApiController {

    @Autowired
    private SonorousRoomService sonorousRoomService;

    @GetMapping("/api/admin/roomList")
    public ResponseEntity<ResponseDto<Room>> roomList(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Room> list = sonorousRoomService.roomList(pageable);

        ResponseDto<Room> dto = new ResponseDto<Room>();
        dto.setStatus(HttpStatus.OK.value());
        dto.setList(list);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/api/admin/roomInsert")
    public ResponseEntity<Integer> roomInsert(Room room,
                                              List<MultipartFile> files,
                                              MultipartFile thumbnail) throws Exception {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = "";
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }
        sonorousRoomService.insert(room, files, thumbnail, userName);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/api/admin/roomView/{roomId}")
    public ResponseEntity<ResponseDto<Room>> roomView(@PathVariable Integer roomId) {
        Room roomDetail = sonorousRoomService.roomView(roomId);

        ResponseDto<Room> dto = new ResponseDto<Room>();
        dto.setStatus(HttpStatus.OK.value());
        dto.setData(roomDetail);
        dto.setRoomImages(roomDetail.getImages());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/admin/roomUpdatePage/{roomId}")
    public ResponseEntity<ResponseDto<Room>> roomUpdatePage(@PathVariable Integer roomId){
        Room roomDetail = sonorousRoomService.roomView(roomId);

        ResponseDto<Room> dto = new ResponseDto<Room>();
        dto.setStatus(HttpStatus.OK.value());
        dto.setData(roomDetail);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/api/admin/roomUpdate/{roomId}")
    public ResponseEntity<Integer> roomUpdate(@PathVariable Integer roomId, Room room) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = "";
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }

        sonorousRoomService.update(roomId, room, userName);

        return ResponseEntity.ok(1);
    }

    @DeleteMapping("/api/admin/roomDelete/{roomId}")
    public ResponseEntity<Integer> roomDelete(@PathVariable Integer roomId){

        sonorousRoomService.delete(roomId);

        return ResponseEntity.ok(1);
    }
}
