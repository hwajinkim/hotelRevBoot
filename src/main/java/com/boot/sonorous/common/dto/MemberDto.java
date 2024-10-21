package com.boot.sonorous.common.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String username;
    private String password;
    private String country;
    private String ename;
    private String birth;
    private String phone;
    private String email;
    private List<String> roles;

}
