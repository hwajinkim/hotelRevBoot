package com.boot.sonorous.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "T_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "M_ID")
    private String mId;

    @Column(name = "M_PW")
    private String mPw;

    @Column(name = "M_AUTH")
    private String mAuth;
    private String country;
    private String eName;
    private String kName;
    private String birth;
    private String phone;
    private String email;
    private String insDate;
    private String modDate;

}
