package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LIkeController {

    private final LikeService likeService;

    @PostMapping("/switch")
    public ResponseEntity<String> toggleLike(@RequestBody Map<String, Integer> request, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            log.info("좋아요 요청");
            String userId = userDetails.getUsername();
            int contentId = request.get("contentId");

            if (userId == null || contentId == 0) {
                return ResponseEntity.badRequest().body("잘못된 요청입니다.");
            }

            boolean isLiked = likeService.toggleLike(userId, contentId);

            log.info(isLiked ? "좋아요 완료" : "좋아요 취소");
            return ResponseEntity.ok(isLiked ? "좋아요 완료!" : "좋아요 취소!");
        } catch (Exception e) {
            log.error("좋아요 작동 중 오류 발생 = {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 처리 중 오류가 발생했습니다.");
        }
    }


}
