package com.boot.sonorous.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ROOM_IMAGE")
public class RoomImage_241011 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_IMAGE_ID")
    private int Id;

    private String roomImageName;
    private String roomImagePath;

    @OneToOne
    @JoinColumn(name="room_id",  updatable = false)
    private Room room;
}
