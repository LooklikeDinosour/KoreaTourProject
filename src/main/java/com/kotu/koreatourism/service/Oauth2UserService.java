package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                //리소스 서버에 사용자 정보 요청 후 사용자 정보를 담은 객체 받아오기
                OAuth2User user = super.loadUser(userRequest);

                //유저가
                Map<String, Object> attributes = user.getAttributes();

                String sub = (String) attributes.get("sub");
                String provider = "google";
                String id = provider + sub;
                String email = (String)attributes.get("email");
                String name = (String)attributes.get("name");

                SiteUser isUser = userMapper.findByUserId(email);
                if (isUser == null) {
                        SiteUser newUserInfo = new SiteUser();
                        newUserInfo.setUserId(id);
                        newUserInfo.setUserEmail(email);
                        newUserInfo.setUserNickname(name);
                        newUserInfo.setUserRole("ROLE_USER");
                        newUserInfo.setProvider(provider);
                        newUserInfo.setProviderId(sub);

                        userMapper.signUp(newUserInfo);
                }
                return user;
        }

}
