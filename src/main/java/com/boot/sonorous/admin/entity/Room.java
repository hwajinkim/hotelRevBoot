package com.boot.sonorous.admin.entity;

import com.boot.sonorous.common.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "ROOM")
public class Room extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private int Id;

    private String roomName;
    private String roomSize;
    private String bedType;
    private String roomPrice;
    private String roomAmount;
    private String peopleNum;
    private String roomSpec;
    private String roomThumbnailPath;
    private String insId;
    private String modId;

    // 방과 방이미지의 1:N 관계 설정
    // cascade = CascadeType.ALL : 방 저장 - 방 이미지 저장, 방 삭제 - 방 이미지 삭제
    // orphanRemoval = true : 방 이미지 방에서 제거 -> 해당 이미지 DB에서 자동 삭제
    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomImage> images = new ArrayList<>();

    public void addImage(RoomImage image) {
        images.add(image);
        image.setRoom(this);
    }

    public void removeImage(RoomImage image) {
        images.remove(image);
        image.setRoom(null);
    }
}
