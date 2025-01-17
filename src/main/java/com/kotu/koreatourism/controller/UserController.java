package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.dto.tour.TourPlaceSaveDTO;
import com.kotu.koreatourism.service.BoardService;
import com.kotu.koreatourism.service.TourPlaceSaveService;
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

import java.security.Principal;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TourPlaceSaveService tourPlaceSaveService;
    private final BoardService boardService;

    //회원가입
    @GetMapping("/signup")
    public String signUp(Model model) {
        log.info("회원가입");
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
        log.info("회원가입 기입 데이터 전송");

        if(bindingResult.hasErrors()) {
            log.info("회원가입 에러 = {}", bindingResult);
            return "login/signUp";
        }

        userService.signUp(userForm);
        log.info("회원가입 완료");
        return "main/mainPage";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, Principal principal) {
        String userId = principal.getName();
        log.info("접속유저 아이디 = {}", userId);
        LoginDTO userInfo = userService.findByUserId(userId);
        String userNickname = userInfo.getUserNickname();

        List<TourPlaceSaveDTO> myPlaceList = tourPlaceSaveService.findAllPlace(userId);
        model.addAttribute("myPlaceList", myPlaceList);

        //문제 아이디가 작성자가 아닌 닉네임 작성임.. DB 수정
        List<Board> myAllPost = boardService.findMyAllPost(userNickname);
        model.addAttribute("myAllPost", myAllPost);

        return "user/mypage";
    }

    @GetMapping("/checkuseridexist")
    public ResponseEntity<Boolean> checkIdExist(@RequestParam("user_id") String userId) {

        boolean isUserId = userService.checkUserIdExist(userId);
        return ResponseEntity.ok(isUserId);
    }



    @GetMapping("/admin")
    public String adminP() {
        return "/admin";
    }
}
