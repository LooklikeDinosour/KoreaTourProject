package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.SignUpFormDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int signUp(SiteUser newUserInfo);

//    public LoginDTO findByUserId(String userId);
    public SiteUser findByUserId(String userId);

    public boolean checkUserIdExist(String userId);

}
