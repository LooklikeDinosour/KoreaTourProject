package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;

public interface UserService {

    public int signUp(SiteUser user);

    public String loginCheck();

}
