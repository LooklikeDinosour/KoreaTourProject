package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.CustomUserDetails;
import com.kotu.koreatourism.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info("로그인 UserId = {}", userId);
        LoginDTO findUserId = userService.findByUserId(userId);

        if(findUserId == null) {
            throw new UsernameNotFoundException(userId + "는 없는 ID입니다.");
        }
        return new CustomUserDetails(findUserId);
    }
}
