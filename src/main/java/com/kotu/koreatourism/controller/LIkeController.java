package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.dto.LikeDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;
import com.kotu.koreatourism.dto.tour.TourPlaceSaveDTO;
import com.kotu.koreatourism.service.LikeService;
import com.kotu.koreatourism.service.TourDeserializerService;
import com.kotu.koreatourism.service.TourLocationService;
import com.kotu.koreatourism.service.TourPlaceSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final TourLocationService tourLocationService;
    private final TourPlaceSaveService tourPlaceSaveService;
    private final TourDeserializerService tourDeserializerService;

    @Value("${openapi.callbackurl.kor}")
    private String callBackUrl;

    @Value("${openapi.servicekeye}")
    private String serviceKey;

    @Value("${openapi.datatype}")
    private String dataType;

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

            //좋아요
            boolean isLiked = likeService.toggleLike(userId, contentId);
            //좋아요 장소 저장
            //누군가가 저장한 장소를 또 저장할 필요 없으니 해당 contentId가 존재하는지 검증하고 없으면 저장로직만들기
            TourPlace isPlaced = tourPlaceSaveService.findContentId(contentId);
            if (isPlaced == null) {

                String placeInfo = tourLocationService.detailCommonInfoAPI(callBackUrl, serviceKey, dataType, contentId);
                TourDetailCommonItemDTO placeCommonInfoList = tourDeserializerService.parsingJsonObject(placeInfo, TourDetailCommonItemDTO.class);
                TourDetailCommonDTO placeCommonInfo = placeCommonInfoList.getTourDetailCommonList().get(0);
                log.info("좋아요 장소 저장  = {}", placeCommonInfo);

                tourPlaceSaveService.savePlace(placeCommonInfo, userId);
            }
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
    public ResponseEntity<List<TourPlace>> getLikesList(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        List<TourPlace> likeList = likeService.getLikeList(userId);

        return ResponseEntity.ok(likeList);
    }

}
