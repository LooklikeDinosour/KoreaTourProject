package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int signUp(SiteUser user);

    public LoginDTO findByUserId(String userId);

}
