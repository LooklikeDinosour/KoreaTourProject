package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/checkid")
    @ResponseBody
    public ResponseEntity<String> checkUserIdExist(@RequestParam("userId") String userId) {
        log.info("회원가입 ID 중복체크 = {}", userId);

        if(userId.trim().isEmpty()) {
            log.info("회원가입 ID 중복체크 null 값 확인 = {}", userId);
            return new ResponseEntity<>("아이디를 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        boolean checkResult = userService.checkUserIdExist(userId);
        if (checkResult) {
            return new ResponseEntity<>("존재하는 아이디입니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("사용할 수 있는 아이디입니다.", HttpStatus.OK);
        }
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
