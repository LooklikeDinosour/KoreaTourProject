package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.ContentType;
import com.kotu.koreatourism.dto.TourAreaCodesDTO;
import com.kotu.koreatourism.dto.TourDetailInfoItemDTO;
import com.kotu.koreatourism.dto.TourLocationDTO;
import com.kotu.koreatourism.service.TourCodeService;
import com.kotu.koreatourism.service.TourLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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


    private final TourCodeService tourCodeService;
    private final TourLocationService tourLocationService;

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<TourAreaCodesDTO> tourInfo() throws IOException, ParseException {

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

        TourAreaCodesDTO items = tourCodeService.parsingJsonObject(areaData, TourAreaCodesDTO.class);

        return new ResponseEntity<TourAreaCodesDTO>(items, HttpStatus.OK) ;
    }

    @GetMapping("/location")
    public String locationTest(Model model) throws IOException {

//        for (ContentType value : ContentType.values()) {
//            log.info("Enum type 보기 = {}", value);
//        }

        // contentType만 불러오면 된다. => 이거를 백엔드에서 검증하기 위해서 백엔드에서 먼저 보내서 그 걸로 프론트를 구성하고
        // 다시 받아서 enum으로 검증해서 넣으면 교차 검증되는것 아닌가?

        String locationList = tourLocationService.locationContentsTypeAPI(callBackUrl, serviceKey, dataType, "12");
        TourLocationDTO tourLocation = tourCodeService.parsingJsonObject(locationList, TourLocationDTO.class);
        //log.info("공공데이터 API 호출 = {}", tourLocation);

        model.addAttribute("contentList", ContentType.values());
        model.addAttribute("tourLocation", tourLocation);

        return "tour/tourlocation";
    }
    @GetMapping("/location/{content-type}")
    public String locationContentList(@PathVariable("content-type") String contentType, Model model) throws IOException {

        //Enum 목록에 있는 건지 다시 검증
        ContentType content = ContentType.fromUrlName(contentType);
        int contentTypeId = content.getContentTypeId();
        log.info("ContentTypeId 인증 검사 = {}", contentTypeId);

        String locationList = tourLocationService.locationContentsTypeAPI(callBackUrl, serviceKey, dataType, String.valueOf(contentTypeId));
        TourLocationDTO tourLocation = tourCodeService.parsingJsonObject(locationList, TourLocationDTO.class);
        log.info("공공데이터 API 호출 = {}", tourLocation);

        model.addAttribute("tourLocation", tourLocation);

        return "tour/tourlocation";
    }


    @GetMapping("/location/detail-info/{contentId}")
    public String detailInfo(@PathVariable("contentId") int contentId,
                             Model model) throws IOException {

//        // 1. URL을 만들기 위한 StringBuilder.
//        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/detailCommon1"); /*URL*/
//        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("defaultYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("firstImageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentId) , "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("addrinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("mapinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("overviewYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
//
//        // 3. URL 객체 생성.
//        URL url = new URL(urlBuilder.toString());
//        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        // 5. 통신을 위한 메소드 SET. 대문자로 작성
//        conn.setRequestMethod("GET");
//        // 6. 통신을 위한 Content-type SET.
//        conn.setRequestProperty("Content-type", "application/json");
//        // 7. 통신 응답 코드 확인.
//        System.out.println("Response code: " + conn.getResponseCode());
//        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
//        BufferedReader rd;
//
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//        }
//        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        // 10. 객체 해제.
//        rd.close();
//        conn.disconnect();
//        // 11. 전달받은 데이터 확인.
//        String tourLocationJson = sb.toString();
//        log.info("공공데이터 API String type= {}", tourLocationJson);
        String LocationDetailInfo = tourLocationService.locationDetailInfoAPI(callBackUrl, serviceKey, dataType, contentId);

        TourDetailInfoItemDTO tourDetailInfo = tourCodeService.parsingJsonObject(LocationDetailInfo, TourDetailInfoItemDTO.class);
        log.info("공공데이터 API 호출 = {}", tourDetailInfo );

        model.addAttribute("tourDetailInfo", tourDetailInfo);

        return "tour/tourDetailInfo";
    }
}
