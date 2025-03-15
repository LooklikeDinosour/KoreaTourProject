package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.service.KakaoLogoutService;
import com.kotu.koreatourism.service.Oauth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.SimpleTimeZone;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final KakaoLogoutService kakaoLogoutService;
    @Value("${spring.security.oauth2.client.registration.kakao.client_id}")
    private String clientId;

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
                         @AuthenticationPrincipal OAuth2User oAuth2User) {

        Object kakaoAccount = oAuth2User.getAttribute("kakao_account");
        HttpSession session = request.getSession(false);
            if(session != null && kakaoAccount != null) {
                String accessToken = (String) session.getAttribute("kakaoAccessToken");
                log.info("Access_token 확인 = {}", accessToken);

                //카카오 로그아웃
                if (accessToken != null && !accessToken.isEmpty()) {
                    log.info("kakao 로그아웃 실행");
                    kakaoLogoutService.kakaoLogout1(accessToken);
                }
            }


        //Spring Security 로그아웃
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        log.info("로그아웃 완료");
        return "redirect:/";
    }

}
