package com.boot.sonorous.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SignInDTO {
    private String username;
    private String password;
}
