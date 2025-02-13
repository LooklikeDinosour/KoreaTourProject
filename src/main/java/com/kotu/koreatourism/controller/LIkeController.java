package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.dto.LikeDTO;
import com.kotu.koreatourism.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LIkeController {

    private final LikeService likeService;

    @PostMapping("/switch")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody Map<String, Integer> request,
                                                          @AuthenticationPrincipal UserDetails userDetails) {

        Map<String, Object> response = new HashMap<>();

        try {
            log.info("좋아요 요청");
            String userId = userDetails.getUsername();
            int contentId = request.get("contentId");

            if (userId == null || contentId == 0) {
                response.put("isLiked", false);
                response.put("message", "잘못된 요청입니다.");
                return ResponseEntity.badRequest().body(response);
            }

            boolean isLiked = likeService.toggleLike(userId, contentId);

            log.info(isLiked ? "좋아요 완료" : "좋아요 취소");
            response.put("isLiked", isLiked);
            response.put("message", isLiked ? "좋아요 완료" : "좋아요 취소");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("좋아요 작동 중 오류 발생 = {}", e);
            response.put("isLiked", false);
            response.put("message", "좋아요 처리 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<LikeDTO>> getLikesList(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        likeService.getLikeList(userId);


    }

}
