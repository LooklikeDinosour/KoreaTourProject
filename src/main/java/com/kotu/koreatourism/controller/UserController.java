package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    //회원가입
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("userForm", new SignUpFormDTO());
        return "login/signUp";
    }
    @PostMapping("/signup")
    public String signUpProcess(@Validated @ModelAttribute("userForm") SignUpFormDTO userForm, BindingResult bindingResult) {
        log.info("회원가입");

        if(bindingResult.hasErrors()) {
            log.info("회원가입 에러 = {}", bindingResult);
            return "login/signUp";
        }

        userService.signUp(userForm);
        return "main/mainPage";
    }

    @GetMapping("/admin")
    public String adminP() {
        return "/admin";
    }
}
