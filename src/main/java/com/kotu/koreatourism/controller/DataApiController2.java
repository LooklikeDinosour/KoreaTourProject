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
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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


    private final TourDeserializerService tourDeserializerService;
    private final TourLocationService tourLocationService;
    private final TourCategoryService tourCategoryService;

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<TourAreaCodeItemDTO> tourInfo() throws IOException, ParseException {

        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/areaCode1"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8")); /*한글 국가명*/
        //urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*ISO 2자리코드*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*ISO 2자리코드*/
        // 3. URL 객체 생성.
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET. 대문자로 작성
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        String areaData = sb.toString();
        //log.info("공공데이터 API 호출 = {}", areaData );

        TourAreaCodeItemDTO items = tourDeserializerService.parsingJsonObject(areaData, TourAreaCodeItemDTO.class);

        return new ResponseEntity<TourAreaCodeItemDTO>(items, HttpStatus.OK) ;
    }

    @GetMapping("/location")
    public String location(@RequestParam(value = "latitude", required = false) String latitude,
                           @RequestParam(value = "longitude", required = false) String longitude,
                           HttpSession session,
                           HttpServletRequest request,
                           Model model) throws IOException {

//        for (ContentType value : ContentType.values()) {
//            log.info("Enum type 보기 = {}", value);
//        }

        // contentType만 불러오면 된다. => 이거를 백엔드에서 검증하기 위해서 백엔드에서 먼저 보내서 그 걸로 프론트를 구성하고
        // 다시 받아서 enum으로 검증해서 넣으면 교차 검증되는것 아닌가?
        log.info("location URL 확인하기 = {} ", request.getRequestURL());
        log.info("location URI 확인하기 = {} ", request.getRequestURI());
        log.info("location URL 쿼리스트링 확인하기 = {} ", request.getQueryString());

        log.info("location 좌표찍어보자 기본 Y = {}, X = {} ", latitude, longitude);

        session.setAttribute("longitude", longitude);
        session.setAttribute("latitude", latitude);

        String locationBasedList = tourLocationService.locationBasedAPI(callBackUrl, serviceKey, dataType, "12", longitude, latitude);
        TourLocationBasedItemDTO tourLocationBased = tourDeserializerService.parsingJsonObject(locationBasedList, TourLocationBasedItemDTO.class);
        log.info("location 공공데이터 API 호출 = {}", tourLocationBased);

        model.addAttribute("category", ContentType.values());
        model.addAttribute("tourLocationBased", tourLocationBased);

        return "tour/tourlocation";
   }
    @GetMapping("/location/{content-type}")
    public String locationCategory(@PathVariable("content-type") String contentType,
                                   @RequestParam(value = "latitude", required = false) String latitude1,
                                   @RequestParam(value = "longitude", required = false) String longitude1,
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
        log.info("LC @RequestParam 찍어보자  Y = {}, X = {} ", latitude1, longitude1);

        String locationBasedList = tourLocationService.locationBasedAPI(callBackUrl, serviceKey, dataType, String.valueOf(contentTypeId), longitude, latitude);
        TourLocationBasedItemDTO tourLocationBased = tourDeserializerService.parsingJsonObject(locationBasedList, TourLocationBasedItemDTO.class);
        log.info("LC 공공데이터 API 호출 = {}", tourLocationBased);

        model.addAttribute("category", ContentType.values());
        model.addAttribute("tourLocationBased", tourLocationBased);

        return "tour/tourlocation";
    }

    @GetMapping("/location/{content-type}/detail-info/{contentId}")
    public String detailIntro(@PathVariable("content-type") String contentType,
                             @PathVariable("contentId") int contentId,
                             Model model) throws IOException {

        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();

        log.info("컨텐트 타입이 숫자일까 문자일까? = {}", contentType);
        String locationCommonInfo = tourLocationService.detailCommonInfoAPI(callBackUrl, serviceKey, dataType, contentId);
        String locationDetailIntro = tourLocationService.detailIntroAPI(callBackUrl, serviceKey, dataType, contentId, contentTypeId);
        TourDetailCommonItemDTO tourCommonInfo = tourDeserializerService.parsingJsonObject(locationCommonInfo, TourDetailCommonItemDTO.class);
        TourDetailIntroItemDTO tourDetailIntro = tourDeserializerService.parsingJsonObject(locationDetailIntro, TourDetailIntroItemDTO.class);
        log.info("공공데이터 API 호출 = {}", tourDetailIntro );

        model.addAttribute("tourCommonInfo", tourCommonInfo);
        model.addAttribute("tourDetailIntro", tourDetailIntro);

        return "tour/tourDetailInfo";
    }


    @GetMapping("/getcategory")
    @ResponseBody
    public ResponseEntity<List<TourAreaCategoryDTO>> getCategory() {
        List<TourAreaCategoryDTO> takeAreaCategory = tourCategoryService.takeCategory();
        log.info("takeCategoryList = {}", takeAreaCategory);
        return new ResponseEntity<>(takeAreaCategory, HttpStatus.OK);
    }

    @GetMapping("/getcategorychild/{gi}/{cl}/{cdo}")
    @ResponseBody
    public ResponseEntity<List<TourAreaCategoryDTO>> getCategoryChild(@PathVariable("gi") String groupId,
                                                                      @PathVariable("cl") int categoryLv,
                                                                      @PathVariable("cdo") int categoryDetailLv,
                                                                      HttpServletRequest request) {


        log.info("LC URL 확인하기 = {} ", request.getRequestURL());
        log.info("LC URI 확인하기 = {} ", request.getRequestURI());
        log.info("LC URL 쿼리스트링 확인하기 = {} ", request.getQueryString());
        log.info("gi = {}, cl = {}, cdo = {}", groupId, categoryLv, categoryDetailLv);
        TourAreaCategoryDTO tourAreaCategoryDTO = new TourAreaCategoryDTO();
        tourAreaCategoryDTO.setGroupId(groupId);
        tourAreaCategoryDTO.setCategoryLv(categoryLv);
        tourAreaCategoryDTO.setCategoryDetailOr(categoryDetailLv);

        List<TourAreaCategoryDTO> responseAreaCategoryChild = tourCategoryService.takeCategoryChild(tourAreaCategoryDTO);

        return new ResponseEntity<>(responseAreaCategoryChild, HttpStatus.OK);
    }

    @GetMapping("/area-based")
    public String areaBased(Model model) throws IOException {
        //tourCategoryService.takeCategory();

        String areaBasedList = tourLocationService.areaBasedAPI(callBackUrl, serviceKey, dataType);
        //일단 해당 항목이 같으니까 써보기
        TourLocationBasedItemDTO tourAreaBased = tourDeserializerService.parsingJsonObject(areaBasedList, TourLocationBasedItemDTO.class);
        log.info("AreaBased 데이터 = {}", tourAreaBased);

        model.addAttribute("tourAreaBased", tourAreaBased);

        return "tour/tourarea";
    }
}
