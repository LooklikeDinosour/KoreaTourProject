package com.kotu.koreatourism.dto;

import com.kotu.koreatourism.domain.SiteUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final SiteUser siteUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        log.info("siteUser ={}", siteUser.toString());
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add( new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return siteUser.getUserRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return siteUser.getUserPassword();
    }

    @Override
    public String getUsername() {
        return siteUser.getUserId();
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
