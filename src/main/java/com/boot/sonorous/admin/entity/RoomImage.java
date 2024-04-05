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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="room_id")
    private Room room;
}
