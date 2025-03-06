package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.CustomUserDetails;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.mapper.UserMapper;
import com.mysql.cj.log.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

        private final UserMapper userMapper;
        private final UserService userService;

        @Override
        public OAuth2User loadUser(OAuth2UserRequest oAuthUserRequest) throws OAuth2AuthenticationException {
                //리소스 서버에 사용자 정보 요청 후 사용자 정보를 담은 객체 받아오기
                OAuth2User oAuth2User = super.loadUser(oAuthUserRequest);
                log.info("Oauth 리소스 서버 사용자 정보 객체 = {}", oAuth2User.getAttributes());

                //유저
                Map<String, Object> attributes = oAuth2User.getAttributes();

                String sub = (String) attributes.get("sub");
                String provider = "google";
                String id = provider + sub;
                String email = (String)attributes.get("email");
                String name = (String)attributes.get("name");

                boolean isExistUserMail = userMapper.findByUserEmail(email);
                log.info("기존 회원 인지 확인 = {}", isExistUserMail);
                if (isExistUserMail == false) {
                        log.info("Oauth 회원가입 = {}", email);
                        userService.signUp(new SignUpFormDTO(id, email, name, provider, sub));
                }

                SiteUser user = userMapper.findByUserId(id);

                if (user == null) {
                        throw new OAuth2AuthenticationException("OAuth 회원 정보를 찾을 수 없습니다.");
                }

                LoginDTO byUserId = userService.findByUserId(id);

                return new CustomUserDetails(byUserId, attributes, Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
        }

}
