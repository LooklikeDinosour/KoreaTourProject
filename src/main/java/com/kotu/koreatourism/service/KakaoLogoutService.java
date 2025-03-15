package com.kotu.koreatourism.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLogoutService {
   private final RestTemplate restTemplate = new RestTemplate();

//    public RestTemplate createTrustAllRestTemplate() {
//      try {
//         TrustManager[] trustAllCerts = new TrustManager[] {
//                 new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() { return null; }
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
//                 }
//         };
//
//         SSLContext sslContext = SSLContext.getInstance("TLS");
//         sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//         HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//         HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
//
//         return new RestTemplate();
//      } catch (Exception e) {
//         throw new RuntimeException(e);
//      }
//   }

   public void kakaoLogout1(String accessToken) {

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

   public void kakaoLogout2(String accessToken, String clientId) {
//      RestTemplate restTemplate = createTrustAllRestTemplate();
//
      log.info("client Id 확인 = {}", clientId);

      String logoutUrl = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/logout")
              .queryParam("client_id", clientId)
              .queryParam("logout_redirect_uri", "https://localhost:8443")
              .build()
              .toUriString();

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.set("Authorization", "Bearer " + accessToken);
      httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      HttpEntity<String> request = new HttpEntity<>(httpHeaders);

//      ResponseEntity<String> response = restTemplate.exchange(logoutUrl, HttpMethod.POST, request, String.class);
      ResponseEntity<String> response = restTemplate.getForEntity(logoutUrl, String.class);

      if(response.getStatusCode().is2xxSuccessful()) {
         log.info("kakao 로그아웃 성공");
      } else {
         log.error("kakao 로그아웃 실패 = {}", response.getBody());
      }
   }

   public void kakaoLogout3(String accessToken) {
      // 카카오 연결 해제(unlink) API 사용
      String unlinkUrl = "https://kapi.kakao.com/v1/user/unlink";

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.set("Authorization", "Bearer " + accessToken);
      httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      HttpEntity<String> request = new HttpEntity<>(httpHeaders);

      try {
         ResponseEntity<String> response = restTemplate.exchange(
                 unlinkUrl,
                 HttpMethod.POST,
                 request,
                 String.class
         );

         if(response.getStatusCode().is2xxSuccessful()) {
            log.info("카카오 연결 해제(unlink) 성공: {}", response.getBody());
         } else {
            log.error("카카오 연결 해제(unlink) 실패: {}", response.getBody());
         }
      } catch (Exception e) {
         log.error("카카오 연결 해제(unlink) 처리 중 오류 발생", e);
      }
   }
}
