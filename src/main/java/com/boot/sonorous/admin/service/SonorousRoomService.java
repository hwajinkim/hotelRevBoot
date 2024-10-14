package com.boot.sonorous.admin.service;

import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.admin.entity.RoomImage;
import com.boot.sonorous.admin.repository.SonorousRoomImageRepository;
import com.boot.sonorous.admin.repository.SonorousRoomRepository;
import com.boot.sonorous.common.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SonorousRoomService {
    @Autowired
    SonorousRoomRepository sonorousRoomRepository;
    @Autowired
    SonorousRoomImageRepository sonorousRoomImageRepository;

    @Transactional(readOnly = true)
    public Page<Room> roomList(Pageable pageable) {
        return sonorousRoomRepository.findAll(pageable);
    }

    @Transactional
    public void insert(Room room,
                       List<MultipartFile> files,
                       MultipartFile thumbnail,
                       String userName) throws Exception{
        List<RoomImage> saveImages = new ArrayList<>();

        // 1. 방이미지 파일 업로드
        for(MultipartFile file : files){
            if(file.getName() != "") {
                String fileName = FileUtil.fileUpload(file);

                // 2. 이미지 Path RoomImage 객체로 변환 후 Room에 추가
                RoomImage roomImage = new RoomImage();
                roomImage.setRoom(room);
                roomImage.setRoomImageName(fileName);
                roomImage.setRoomImagePath("/files/" + fileName);
                saveImages.add(roomImage);
            }
        }
        String thumbnailName = FileUtil.thumbnailUpload(thumbnail);
        room.setRoomThumbnailPath("/files/thumbnails/" + thumbnailName);
        room.setImages(saveImages);
        room.setInsId(userName);
        sonorousRoomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public Room roomView(Integer id) {
        return sonorousRoomRepository.findById(id)
            .orElseThrow(()->{
               return new IllegalArgumentException("방 상세보기 실패 : 아이디를 찾을 수 없습니다.");
            });
    }

    @Transactional
    public void update(int id, Room room, RoomImage roomImage) {
        Room roomInfo = sonorousRoomRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("방 찾기 실패 : 아이디를 찾을 수 없음.");
            });

        roomInfo.setRoomName(room.getRoomName());
        roomInfo.setRoomSize(room.getRoomSize());
        roomInfo.setBedType(room.getBedType());
        roomInfo.setRoomPrice(room.getRoomPrice());
        roomInfo.setRoomAmount(room.getRoomAmount());
        roomInfo.setPeopleNum(room.getPeopleNum());
        roomInfo.setRoomSpec(room.getRoomSpec());
    }

    @Transactional
    public void delete(int id) {
        if(!sonorousRoomRepository.existsById(id)){
            throw new RuntimeException("방 삭제 실패 : 아이디를 찾을 수 없음.");
        }
        sonorousRoomRepository.deleteById(id);
    }
}
