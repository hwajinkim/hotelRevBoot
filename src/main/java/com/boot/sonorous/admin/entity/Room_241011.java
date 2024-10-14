package com.boot.sonorous.admin.entity;

import com.boot.sonorous.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "roomImage")
@Table(name = "ROOM")
public class Room_241011 extends BaseTimeEntity{

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
    private String modId;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomImage roomImage;
}
