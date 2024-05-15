package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import com.kotu.koreatourism.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
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
        userInfo.setUserRole("ROLE_USER");
        userInfo.setUserPassword(bCryptPasswordEncoder.encode(userInfo.getUserPassword()));

        return userMapper.signUp(userInfo);
    }

    @Override
    public LoginDTO findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }
}
