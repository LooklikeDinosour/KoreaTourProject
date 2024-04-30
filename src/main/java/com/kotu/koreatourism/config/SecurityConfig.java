package com.kotu.koreatourism.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/main","/login","/signup","/loginProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/board/**","/message/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()// 특정한 경로에 작업하고 싶으면 설정하는 메서드
                       );

        //로그인이되어있지 않은 상태에서 /설정주소에 접근하면 login 페이지로 리다이렉션됌.
        http
                .formLogin((auth) -> auth
                        .usernameParameter("userId")
                        .passwordParameter("userPassword")
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/main")
                        .permitAll()
                );

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/main")
                );


        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true) //true면 설정 갯수 초과시 신규 로그인 불가, false는 기존세션 1개를 종료하고 접속시킴
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
