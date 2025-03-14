package com.kotu.koreatourism.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLogoutService {
   private final RestTemplate restTemplate = new RestTemplate();

   public void kakaoLogout(String accessToken) {

      String logoutUrl = "https://kapi.kakao.com/v1/user/logout";

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.set("Authorization", "Bearer " + accessToken);
      httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      HttpEntity<String> request = new HttpEntity<>(httpHeaders);

      ResponseEntity<String> response = restTemplate.exchange(logoutUrl, HttpMethod.POST, request, String.class);

      if(response.getStatusCode().is2xxSuccessful()) {
         log.info("kakao 로그아웃 성공");
      } else {
         log.error("kakao 로그아웃 실패 = {}", response.getBody());
      }
   }
}
