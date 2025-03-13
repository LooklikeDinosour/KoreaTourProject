package com.kotu.koreatourism.config;

import com.kotu.koreatourism.service.Oauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Oauth2UserService oauth2UserService;

    public SecurityConfig(Oauth2UserService oauth2UserService) {
        this.oauth2UserService = oauth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/checkid", "/signup", "/loginProc", "/api/**", "/board/**", "/static/**", "/js/**", "/css/**", "/images/**", "/fragments/**", "/layouts/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/place/save", "user/mypage", "comment/save", "likes/switch").authenticated()
                        .requestMatchers("/message/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()// 특정한 경로에 작업하고 싶으면 설정하는 메서드
                );

        //로그인이되어있지 않은 상태에서 /설정주소에 접근하면 login 페이지로 리다이렉션됌.
        httpSecurity
                .formLogin((auth) -> auth
                        .usernameParameter("userId")
                        .passwordParameter("userPassword")
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .failureHandler((request, response, exception) -> {
                            String errorMessage = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
                            request.getSession().setAttribute("error", errorMessage);
                            response.sendRedirect("/login?error");
                        })
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );

        httpSecurity
                .oauth2Login((oauth) -> oauth.loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService))
                        .defaultSuccessUrl("/")
                );

        httpSecurity
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                );


        httpSecurity
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true) //true면 설정 갯수 초과시 신규 로그인 불가, false는 기존세션 1개를 종료하고 접속시킴
                );

        return httpSecurity.build();

    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
