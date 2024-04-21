package com.boot.sonorous.admin.service;

import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.admin.entity.RoomImage;
import com.boot.sonorous.admin.repository.SonorousRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class SonorousRoomService {
    @Autowired
    SonorousRoomRepository sonorousRoomRepository;

    public Page<RoomImage> roomList(Pageable pageable) {
        return sonorousRoomRepository.findAll(pageable);
    }

    public void insert(RoomImage roomImage, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        roomImage.setRoomImageName(fileName);
        roomImage.setRoomImagePath("/files/" + fileName);

        sonorousRoomRepository.save(roomImage);
    }

    public RoomImage roomView(Integer id) {
        return sonorousRoomRepository.findById(id).orElse(null);
    }

    public void roomDelete(int id) {
        sonorousRoomRepository.deleteById(id);
    }

    public void update(RoomImage roomTemp) {
        sonorousRoomRepository.save(roomTemp);
    }
}
