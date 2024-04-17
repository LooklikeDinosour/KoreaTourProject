package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    //로그인
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }
    //회원가입
    @GetMapping("/signup")
    public String signUp() {
        return "login/signUp";
    }
    @PostMapping("/signup")
    public String signUpProcess(@ModelAttribute SiteUser siteUser) {
        log.info("회원가입");

        //회원가입일자 생성하기
        //String createDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        siteUser.setCreateDate(LocalDateTime.now());
        siteUser.setUserRole("ROLE_USER");
        log.info("SiteUser Info={}", siteUser.toString());
        userService.signUp(siteUser);

        return "main/mainPage";
    }

    @GetMapping("/admin")
    public String adminP() {
        return "/admin";
    }
}
