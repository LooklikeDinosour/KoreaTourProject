package com.kotu.koreatourism;

import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class SignUpTest {
    @Autowired
    UserService userService;

    @DisplayName("회원가입 테스트")
    @Test
    public void signUpTest() {

        //given
        SignUpFormDTO newUser = new SignUpFormDTO();
        newUser.setUserId("kotutest12345");
        newUser.setUserPassword("Kotutest12345!");
        newUser.setUserEmail("test@mail.com");
        newUser.setUserPhone("010-2342-4343");
        newUser.setUserNickname("테스트1");

        //when
        int result = userService.signUp(newUser);

        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @DisplayName("회원 중복확인 테스트")
    @Test
    public void duplicatedTest() {

        //given
        SignUpFormDTO newUser = new SignUpFormDTO();
        newUser.setUserId("kotutest12345");
        newUser.setUserPassword("Kotutest12345!");
        newUser.setUserEmail("test@mail.com");
        newUser.setUserPhone("010-2342-4343");
        newUser.setUserNickname("테스트1");
        userService.signUp(newUser);

        //when
        LoginDTO newLoginDTO = userService.findByUserId("kotutest12345");

        //then
        Assertions.assertThat(newLoginDTO.getUserId()).isEqualTo(newUser.getUserId());
    }

}
