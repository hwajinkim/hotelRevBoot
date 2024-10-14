package com.boot.sonorous.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ROOM_IMAGE")
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_IMAGE_ID")
    private int Id;

    private String roomImageName;
    private String roomImagePath;

    // 방이미지와 방의 N:1 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
