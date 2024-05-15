package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;

public interface UserService {

    public int signUp(SignUpFormDTO userInfo);

    public LoginDTO findByUserId(String userId);
}
