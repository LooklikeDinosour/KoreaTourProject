package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.service.TourPlaceSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class TourPlaceSaveController {

    private final TourPlaceSaveService tourPlaceSaveService;

    @PostMapping("/save")
    @ResponseBody
    public boolean savePlace(@RequestBody TourPlace placeInfo, @AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        log.info("유저 아이디 = {}", userId);

        return tourPlaceSaveService.savePlace(placeInfo, userId);
    }

    @DeleteMapping("/delete/{placeId}")
    public void deletePlace(@PathVariable int placeId) {
        tourPlaceSaveService.deletePlace(placeId);
    }

    @GetMapping("/allPlace")
    public List<TourPlace> findAllPlace(Principal principal) {
        String userId = principal.getName();
        return tourPlaceSaveService.findAllPlace(userId);
    }
}
