package com.boot.sonorous.common.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusCode {
    // 정상 status
    OK(200, "정상 처리 완료"),

    // 잘못된 요청 status
    DUPLICATE_ACCOUNT(452, "이미 존재하는 계정입니다."),
    NOT_MATCH_PASSWORD(453, "비밀번호가 재확인 비밀번호와 일치하는지 확인해주십시오."),
    NOT_EXIST_ACCOUNT(454, "존재하지 않는 계정입니다."),
    INCORRECT_PASSWORD(455, "비밀번호를 다시 확인해주십시오.");


    private final int statusCode;
    private final String status;

}
