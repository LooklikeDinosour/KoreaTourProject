package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;
import com.kotu.koreatourism.dto.tour.TourPlaceSaveDTO;
import com.kotu.koreatourism.service.TourDeserializerService;
import com.kotu.koreatourism.service.TourLocationService;
import com.kotu.koreatourism.service.TourPlaceSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class TourPlaceSaveController {

    private final TourPlaceSaveService tourPlaceSaveService;
    private final TourLocationService tourLocationService;
    private final TourDeserializerService tourDeserializerService;

    @Value("${openapi.callbackurl.kor}")
    private String callBackUrl;

    @Value("${openapi.servicekeye}")
    private String serviceKey;

    @Value("${openapi.datatype}")
    private String dataType;

    @PostMapping("/save")
    @ResponseBody
    public boolean savePlace(@RequestBody Map<String, Integer> contentInfo, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        String userId = userDetails.getUsername();
        Integer contentId = contentInfo.get("contentId");
        log.info("content 아이디 = {}", contentId);
        String placeInfo = tourLocationService.detailCommonInfoAPI(callBackUrl, serviceKey, dataType, contentId);
        TourDetailCommonItemDTO placeCommonInfoList = tourDeserializerService.parsingJsonObject(placeInfo, TourDetailCommonItemDTO.class);
        TourDetailCommonDTO placeCommonInfo = placeCommonInfoList.getTourDetailCommonList().get(0);
        log.info("해당 장소 정보 = {}", placeCommonInfo);
        return tourPlaceSaveService.savePlace(placeCommonInfo, userId);
    }

    @DeleteMapping("/delete/{placeId}")
    public void deletePlace(@PathVariable int placeId) {
        tourPlaceSaveService.deletePlace(placeId);
    }

}
