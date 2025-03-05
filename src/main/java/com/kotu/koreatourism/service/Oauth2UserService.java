package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.CustomUserDetails;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

        private final UserMapper userMapper;

        @Override
        public OAuth2User loadUser(OAuth2UserRequest oAuthUserRequest) throws OAuth2AuthenticationException {
                //리소스 서버에 사용자 정보 요청 후 사용자 정보를 담은 객체 받아오기
                OAuth2User oAuth2User = super.loadUser(oAuthUserRequest);
                log.info("Oauth 리소스 서버 사용자 정보 객체 = {}", oAuth2User);
                //유저가
                Map<String, Object> attributes = oAuth2User.getAttributes();

                ClientRegistration userInfoEndpoint = oAuthUserRequest.getClientRegistration();

                String sub = (String) attributes.get("sub");
                String provider = "google";
                String id = provider + sub;
                String email = (String)attributes.get("email");
                String name = (String)attributes.get("name");

                SiteUser member;
                boolean isUserMail = userMapper.findByUserEmail(email);
                log.info("기존 회원 인지 확인 = {}", isUserMail);
                if (isUserMail == false) {
                        log.info("Oauth 회원가입");
                        SiteUser newUserInfo = new SiteUser();
                        newUserInfo.setUserId(id);
                        newUserInfo.setUserEmail(email);
                        newUserInfo.setUserNickname(name);
                        newUserInfo.setUserRole("ROLE_USER");
                        newUserInfo.setProvider(provider);
                        newUserInfo.setProviderId(sub);
                        userMapper.signUp(newUserInfo);
                }
                member = userMapper.findByUserId(id);
                return oAuth2User;
        }

}
