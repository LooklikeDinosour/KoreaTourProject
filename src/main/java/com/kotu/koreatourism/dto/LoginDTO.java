package com.kotu.koreatourism.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String userId; // 아이디 PK
    private String userPassword; // 비밀번호
    private String userNickname; //이름
    private String userRole;
}
