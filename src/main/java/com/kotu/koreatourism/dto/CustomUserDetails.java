package com.kotu.koreatourism.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private final LoginDTO user;
    private  Map<String, Object> attributes;
    private final Collection<GrantedAuthority> authorities;

    public CustomUserDetails(LoginDTO user, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public CustomUserDetails(LoginDTO user, Map<String, Object> attributes, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("siteUser = {}", user.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>(this.authorities);
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        return authorities;
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

    //OAuth
    @Override
    public String getName() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
