package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.SiteUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int signUp(SiteUser newUserInfo);

    public SiteUser findByUserId(String userId);

    public boolean checkUserIdExist(String userId);

}
