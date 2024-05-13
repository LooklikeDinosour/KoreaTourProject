package com.kotu.koreatourism.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank
    private String userId; // 아이디 PK
    @NotBlank
    private String userPassword; // 비밀번호
    private String userNickname; //이름
    private String userRole;
}
