package com.kotu.koreatourism.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpFormDTO {

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{10,30}$", message = "아이디는 10~30자 영문 대 소문자, 숫자를 사용해서 작성해주세요.")
    private String userId; // 아이디 PK

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,30}$", message = "비밀번호는 10~30자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String userPassword; // 비밀번호

    @NotBlank(message = "전화는 필수입니다.")
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}" , message = "전화번호 형식은 000-0000-0000입니다")
    private String userPhone; // 전화번호

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail; // 이메일

    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String userNickname; // 닉네임

    private String userRole; // 유저 권한 구분
}