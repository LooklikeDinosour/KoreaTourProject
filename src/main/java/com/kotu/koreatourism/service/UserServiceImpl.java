package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int signUp(SignUpFormDTO userInfo) {


        //ID가 존재유무를 검증하는 로직 필요
        SiteUser newUserInfo = new SiteUser();
        newUserInfo.setUserId(userInfo.getUserId());
        newUserInfo.setUserPassword(bCryptPasswordEncoder.encode(userInfo.getUserPassword()));
        newUserInfo.setUserPhone(userInfo.getUserPhone());
        newUserInfo.setUserEmail(userInfo.getUserEmail());
        newUserInfo.setUserNickname(userInfo.getUserNickname());
        newUserInfo.setUserRole("ROLE_USER");

        return userMapper.signUp(newUserInfo);
    }

    @Override
    public LoginDTO findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails) {
                return (((UserDetails) principal).getUsername());
            } else {
                return principal.toString();
            }
        }
        return null;
    }
}
