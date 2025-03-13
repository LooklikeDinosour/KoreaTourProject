package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.service.KakaoLogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final KakaoLogoutService kakaoLogoutService;

    //로그인
    @GetMapping("/login")
    public String login(Model model) {
        log.info("로그인");

//        if (bindingResult.hasErrors()) {
//            log.info("로그인 에러 = {}", bindingResult);
//            return "login/login";
//        }

        model.addAttribute("LoginDTO", new LoginDTO());
        return "login/login";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(required = false) String accessToken) {

        //카카오 로그아웃
        if (accessToken != null && !accessToken.isEmpty()) {
            log.info("kakao 로그아웃 실행 : AccessToken = {}", accessToken);
            kakaoLogoutService.kakaoLogout(accessToken);
        }

        //Spring Security 로그아웃
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        log.info("로그아웃 완료");
        return "redirect:/";
    }

}
