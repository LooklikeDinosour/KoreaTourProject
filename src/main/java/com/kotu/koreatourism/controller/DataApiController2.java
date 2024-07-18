package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.ContentType;
import com.kotu.koreatourism.dto.tour.*;
import com.kotu.koreatourism.service.TourCategoryService;
import com.kotu.koreatourism.service.TourDeserializerService;
import com.kotu.koreatourism.service.TourLocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class DataApiController2 {

    @Value("${openApi.callbackurl.kor}")
    private String callBackUrl;

    @Value("${openApi.serviceKeyE}")
    private String serviceKey;

    @Value("${openApi.dataType}")
    private String dataType;

    @Value("${googleMapApi}")
    private String googleMapsApiKey;

    private final TourDeserializerService tourDeserializerService;
    private final TourLocationService tourLocationService;
    private final TourCategoryService tourCategoryService;

    //위치기반 GPS 좌표 필요
    //동기식 구성
    @GetMapping("/location")
    public String location(@RequestParam(value = "latitude", required = true) String latitude,
                           @RequestParam(value = "longitude", required = true) String longitude,
                           HttpSession session,
                           HttpServletRequest request,
                           Model model) throws IOException {

        // contentType만 불러오면 된다. => 이거를 백엔드에서 검증하기 위해서 백엔드에서 먼저 보내서 그 걸로 프론트를 구성하고
        // 다시 받아서 enum으로 검증해서 넣으면 교차 검증되는것 아닌가?
        log.info("location URL 확인 = {} ", request.getRequestURL());
        log.info("location URI 확인 = {} ", request.getRequestURI());
        log.info("location URL 쿼리스트링 확인 = {} ", request.getQueryString());

        log.info("location 좌표 기본 Y = {}, X = {} ", latitude, longitude);

        session.setAttribute("longitude", longitude);
        session.setAttribute("latitude", latitude);

        if (latitude == null || longitude == null) {
            log.error("위도, 경도 누락되었습니다.");
            model.addAttribute("errorMessage", "위도, 경도 누락되었습니다.");
            return "error";
        }

        try {
            String locationBasedList = tourLocationService.locationBasedAPI(callBackUrl, serviceKey, dataType, "12", longitude, latitude);
            TourLocationBasedItemDTO tourLocationBased = tourDeserializerService.parsingJsonObject(locationBasedList, TourLocationBasedItemDTO.class);
            log.info("location 공공데이터 API 호출 = {}", tourLocationBased);
            log.info("컨텐츠 메뉴 = {}", ContentType.values());
            model.addAttribute("menu", ContentType.values());
            model.addAttribute("tourLocationBased", tourLocationBased);

            return "tour/tourlocation";

        } catch (Exception e) {
            log.error("Error occurred while processing location data", e);
            model.addAttribute("errorMessage", "An error occurred while processing location data.");
            return "error";
        }
   }
    @GetMapping("/location/{content-type}")
    public String locationCategory(@PathVariable("content-type") String contentType,
//                                   @RequestParam(value = "latitude", required = false) String latitude1,
//                                   @RequestParam(value = "longitude", required = false) String longitude1,
                                   HttpServletRequest request,
                                   HttpSession session,
                                   Model model) throws IOException {

        //Enum 목록에 있는 건지 다시 검증
        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();
        log.info("LC URL 확인하기 = {} ", request.getRequestURL());
        log.info("LC URI 확인하기 = {} ", request.getRequestURI());
        log.info("LC URL 쿼리스트링 확인하기 = {} ", request.getQueryString());
        log.info("LC ContentTypeId 인증 검사 = {}", contentTypeId);

        String longitude = (String)session.getAttribute("longitude");
        String latitude = (String)session.getAttribute("latitude");
        log.info("LC 좌표찍어보자  Y = {}, X = {} ", latitude, longitude);
//        log.info("LC @RequestParam 찍어보자  Y = {}, X = {} ", latitude1, longitude1);

        String locationBasedList = tourLocationService.locationBasedAPI(callBackUrl, serviceKey, dataType, String.valueOf(contentTypeId), longitude, latitude);
        TourLocationBasedItemDTO tourLocationBased = tourDeserializerService.parsingJsonObject(locationBasedList, TourLocationBasedItemDTO.class);
        log.info("LC 공공데이터 API 호출 = {}", tourLocationBased);

        model.addAttribute("menu", ContentType.values());
        model.addAttribute("tourLocationBased", tourLocationBased);

        return "tour/tourlocation";
    }

    @GetMapping("location/{content-type}/detail-info/{contentId}")
    public String detailIntro(@PathVariable("content-type") String contentType,
                             @PathVariable("contentId") int contentId,
                             Model model) throws IOException {

        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();

        String locationCommonInfo = tourLocationService.detailCommonInfoAPI(callBackUrl, serviceKey, dataType, contentId);
        String locationDetailIntro = tourLocationService.detailIntroAPI(callBackUrl, serviceKey, dataType, contentId, contentTypeId);
        TourDetailCommonItemDTO tourCommonInfo = tourDeserializerService.parsingJsonObject(locationCommonInfo, TourDetailCommonItemDTO.class);
        TourDetailIntroItemDTO tourDetailIntro = tourDeserializerService.parsingJsonObject(locationDetailIntro, TourDetailIntroItemDTO.class);
        log.info("공공데이터 DCI API 호출 = {}", tourCommonInfo );
        log.info("공공데이터 DII API 호출 = {}", tourDetailIntro );

        model.addAttribute("tourCommonInfo", tourCommonInfo);
        model.addAttribute("tourDetailIntro", tourDetailIntro);
        model.addAttribute("mapsApiKey", googleMapsApiKey);

        return "tour/tourDetailInfo";
    }

    //지역기반
    //비동기식 구성
    //대분류
    @GetMapping("/area1")
    @ResponseBody
    public ResponseEntity<List<TourAreaCategoryDTO>> getArea1Category() {
        List<TourAreaCategoryDTO> takeArea1Category = tourCategoryService.takeCategory();
        log.info("takeCategoryList = {}", takeArea1Category);
        return new ResponseEntity<>(takeArea1Category, HttpStatus.OK);
    }

    //중분류
    @PostMapping("/area2")
    @ResponseBody
    public ResponseEntity<List<TourAreaCategoryDTO>> getArea2Category(@RequestBody TourAreaCategoryDTO tourArea2,
                                                                      HttpServletRequest request) {

        log.info("LC URI 확인하기 = {} ", request.getRequestURI());
        TourAreaCategoryDTO tourArea2Category = new TourAreaCategoryDTO();
        tourArea2Category.setGroupId(tourArea2.getGroupId());
        tourArea2Category.setCategoryLv(tourArea2.getCategoryLv());
        tourArea2Category.setCategoryDetailOr(tourArea2.getCategoryDetailOr());

        List<TourAreaCategoryDTO> responseArea2Category = tourCategoryService.takeCategoryChild(tourArea2Category);

        return new ResponseEntity<>(responseArea2Category, HttpStatus.OK);
    }
    //공공데이터 API 호출
    @PostMapping("/areabase")
    @ResponseBody
    public ResponseEntity<TourAreaBasedItemDTO> areaBasedAPI(@RequestBody TourAreaSigunguDTO areaSigunguCode,
                                                             @RequestParam String pageNo,
                                                             @RequestParam String numOfRows,
                                                             HttpSession session) throws IOException {

        session.setAttribute("areaSigungu", areaSigunguCode);

        String areaBasedList = tourLocationService.areaBasedAPI(callBackUrl, serviceKey, dataType, 0, numOfRows, pageNo, areaSigunguCode);
        TourAreaBasedItemDTO tourAreaBased = tourDeserializerService.parsingJsonObject(areaBasedList, TourAreaBasedItemDTO.class);
        log.info("AreaBased 데이터 = {}", tourAreaBased);

        return new ResponseEntity<>(tourAreaBased, HttpStatus.OK);
    }

    @GetMapping("/area-based")
    public String areaBased(Model model) {

        model.addAttribute("category", ContentType.values());
        return "tour/tourarea";
    }

    @GetMapping("/area-based-test")
    public String areaBasedTest(Model model) {

        model.addAttribute("category", ContentType.values());
        return "tour/tourarea1";
    }
    @GetMapping("/area-based/{content-type}")
    @ResponseBody
    public ResponseEntity<TourAreaBasedItemDTO> areaCategory(@PathVariable("content-type") String contentType,
                               String pageNo,
                               String numOfRows,
                               HttpServletRequest request,
                               HttpSession session,
                               Model model) throws IOException {

        //Enum 목록에 있는 건지 다시 검증
        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();
        log.info("LC URL 확인하기 = {} ", request.getRequestURL());
        log.info("LC URI 확인하기 = {} ", request.getRequestURI());
        log.info("LC URL 쿼리스트링 확인하기 = {} ", request.getQueryString());
        log.info("LC ContentTypeId 인증 검사 = {}", contentTypeId);
        TourAreaSigunguDTO areaSigungu = (TourAreaSigunguDTO)session.getAttribute("areaSigungu");

        String areaBasedList = tourLocationService.areaBasedAPI(callBackUrl, serviceKey, dataType, contentTypeId, numOfRows, pageNo, areaSigungu);
        TourAreaBasedItemDTO tourAreaBased = tourDeserializerService.parsingJsonObject(areaBasedList, TourAreaBasedItemDTO.class);
        log.info("LC 공공데이터 API 호출 = {}", tourAreaBased);

        model.addAttribute("category", ContentType.values());
        //model.addAttribute("tourLocationBased", tourAreaBased);

        return new ResponseEntity<>(tourAreaBased, HttpStatus.OK);
    }

    @GetMapping("/area-based-test/{content-type}")
    @ResponseBody
    public ResponseEntity<TourAreaBasedItemDTO> areaCategoryTest(@PathVariable("content-type") String contentType,
                                                             String pageNo,
                                                             String numOfRows,
                                                             HttpServletRequest request,
                                                             HttpSession session,
                                                             Model model) throws IOException {

        //Enum 목록에 있는 건지 다시 검증
        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();
        log.info("LC URL 확인하기 = {} ", request.getRequestURL());
        log.info("LC URI 확인하기 = {} ", request.getRequestURI());
        log.info("LC URL 쿼리스트링 확인하기 = {} ", request.getQueryString());
        log.info("LC ContentTypeId 인증 검사 = {}", contentTypeId);
        TourAreaSigunguDTO areaSigungu = (TourAreaSigunguDTO)session.getAttribute("areaSigungu");

        String areaBasedList = tourLocationService.areaBasedAPI(callBackUrl, serviceKey, dataType, contentTypeId, numOfRows, pageNo, areaSigungu);
        TourAreaBasedItemDTO tourAreaBased = tourDeserializerService.parsingJsonObject(areaBasedList, TourAreaBasedItemDTO.class);
        log.info("LC 공공데이터 API 호출 = {}", tourAreaBased);

        model.addAttribute("category", ContentType.values());
        //model.addAttribute("tourLocationBased", tourAreaBased);

        return new ResponseEntity<>(tourAreaBased, HttpStatus.OK);
    }


}
