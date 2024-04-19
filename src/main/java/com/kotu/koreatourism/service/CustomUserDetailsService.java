package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info("UserId ={}", userId);
        //username == userId 기본 매개변수를 따르느라.
        SiteUser findUserId = userService.findByUserId(userId);

        if(findUserId != null) {
            return new CustomUserDetails(findUserId);
        }
        return null;
    }
}
