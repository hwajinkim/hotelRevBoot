package com.boot.sonorous.admin.service;

import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.admin.entity.RoomImage;
import com.boot.sonorous.admin.repository.SonorousRoomImageRepository;
import com.boot.sonorous.admin.repository.SonorousRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class SonorousRoomService_241012 {
    @Autowired
    SonorousRoomRepository sonorousRoomRepository;

    @Autowired
    SonorousRoomImageRepository sonorousRoomImageRepository;

    public Page<RoomImage> roomList(Pageable pageable) {
        return sonorousRoomImageRepository.findAll(pageable);
    }

    public void insert(Room room, RoomImage roomImage, MultipartFile file) throws Exception{

        Room savedRoom = sonorousRoomRepository.save(room);
        roomImage.setRoom(savedRoom);

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        roomImage.setRoomImageName(fileName);
        roomImage.setRoomImagePath("/files/" + fileName);

        sonorousRoomImageRepository.save(roomImage);
    }

    public RoomImage roomView(Integer id) {
        return sonorousRoomImageRepository.findById(id).orElse(null);
    }

    public void roomDelete(int id) {
        sonorousRoomImageRepository.deleteById(id);
    }

    public void update(RoomImage roomTemp) {

        sonorousRoomImageRepository.save(roomTemp);
    }
}
