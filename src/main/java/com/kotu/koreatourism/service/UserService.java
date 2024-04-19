package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;

public interface UserService {

    public int signUp(SiteUser user);

    public LoginDTO findByUserId(String userId);
}
