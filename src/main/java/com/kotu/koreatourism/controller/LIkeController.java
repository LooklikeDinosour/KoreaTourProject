package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LIkeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestParam int contentId, @AuthenticationPrincipal UserDetails userDetails) {
        log.info("좋아요 요청");
        String userId = userDetails.getUsername();
        boolean isLiked = likeService.toggleLike(userId, contentId);

        return ResponseEntity.ok(isLiked ? "좋아요 완료!" : "좋아요 취소!");
    }


}
