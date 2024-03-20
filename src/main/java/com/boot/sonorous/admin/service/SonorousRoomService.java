package com.boot.sonorous.admin.service;

import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.admin.repository.SonorousRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SonorousRoomService {
    @Autowired
    SonorousRoomRepository sonorousRoomRepository;

    public List<Room> roomList() {
        return sonorousRoomRepository.findAll();
    }
}
