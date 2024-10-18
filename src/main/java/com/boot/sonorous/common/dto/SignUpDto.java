package com.boot.sonorous.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String country;
    private String eName;
    private String birth;
    private String phone;
    private String email;
    private List<String> roles;
}
