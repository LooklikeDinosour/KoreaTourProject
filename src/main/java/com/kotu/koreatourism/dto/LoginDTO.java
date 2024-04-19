package com.kotu.koreatourism.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String userId; // 아이디 PK
    private String userPassword; // 비밀번호
    private String userRole;
}
