package com.kotu.koreatourism.dto;

import com.kotu.koreatourism.domain.SiteUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final LoginDTO user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        log.info("siteUser = {}", user.toString());
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add( new GrantedAuthority() {

            @Override
            public String getAuthority() {
                log.info("user Role ={}", user.getUserRole());
                return user.getUserRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
