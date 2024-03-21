package com.boot.sonorous.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "T_ROOM")
public class Room {

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
    private String insId;
    private String insDate;
    private String modId;
    private String modDate;

    @OneToOne
    @JoinColumn(name="roomId")
    private RoomImage roomImage;

}
