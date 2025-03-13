package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public int signUp(SignUpFormDTO userInfo);
    public int signUpOauth(SignUpFormDTO userInfo);

    public LoginDTO findByUserId(String userId);

    public String getCurrentUserName();
    
    public boolean checkUserIdExist(String userId);

    //OAuth 가입시 중복회원 확인절차
    public boolean checkUserEmailExist(String userEmail);


}
