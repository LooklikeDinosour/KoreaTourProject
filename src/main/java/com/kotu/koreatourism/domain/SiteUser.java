package com.kotu.koreatourism.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser {

    @NotBlank(message = "")
    private String userId; // 아이디 PK
    private String userPassword; // 비밀번호
    private String userPhone; // 전화번호
    private String userEmail; // 이메일
    private String userNickname; // 닉네임
    private LocalDateTime createDate; // 생성일자
    private String userRole; // 유저 권한 구분

    @Override
    public String toString() {
        return "SiteUser{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
