package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourAreaCategory;
import com.kotu.koreatourism.dto.tour.TourAreaSigunguDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourLocationServiceImpl implements TourLocationService {

    private final TourCategoryService tourCategoryService;
    @Override
    public String locationBasedAPI(String callBackUrl, String serviceKey, String dataType, String contentTypeId, String mapX, String mapY) throws IOException {

        log.info("X, Y = {} , = {}", mapX, mapY);
        // 위치기반인데 X,Y 좌표를 서울근처로 설정해서 서울만 나오는 거였음
        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/locationBasedList1"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("mapX","UTF-8") + "=" + URLEncoder.encode(mapX, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("mapY","UTF-8") + "=" + URLEncoder.encode(mapY, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(contentTypeId, "UTF-8"));

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
        BufferedReader br;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        br.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        String tourLocationList = sb.toString();
        log.info("공공데이터 API String type= {}", tourLocationList);
        return tourLocationList;
    }

    @Override
    public String detailCommonInfoAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException {
        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/detailCommon1"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("defaultYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("firstImageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("areacodeYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentId) , "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("addrinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("mapinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("overviewYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));

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
        BufferedReader br;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        br.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        String locationDetailInfo = sb.toString();
        //log.info("공공데이터 API String type= {}", tourLocationJson);

        return locationDetailInfo;
    }

    @Override
    public String detailIntroAPI(String callBackUrl, String serviceKey, String dataType, int contentId, int contentType) throws IOException {
        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/detailIntro1"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentId) , "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentType) , "UTF-8"));


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
        BufferedReader br;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        br.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        String locationDetailIntro = sb.toString();
        log.info("LocationDetail String type= {}", locationDetailIntro);

        return locationDetailIntro;
    }

    @Override
    public String areaBasedAPI(String callBackUrl,
                               String serviceKey,
                               String dataType,
                               int contentTypeId,
                               String numOfRows,
                               String pageNo,
                               TourAreaSigunguDTO areaSigunguCode) throws IOException {

        int areaCode = tourCategoryService.takeApiCodeNum(areaSigunguCode.getGroupId());
        int categoryParentLv = areaSigunguCode.getCategoryParentLv();
        int sigunguCode = areaSigunguCode.getCategoryApiCode();
        log.info("지역코드 = {}, 시군구 코드 = {} 부모카테고리레벨 = {}", areaCode, sigunguCode, categoryParentLv);
        StringBuilder urlBuilder = new StringBuilder(callBackUrl + "/areaBasedList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        if (pageNo == null) {
            pageNo = "1";
        }
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("kotu", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A" , "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y" , "UTF-8"));
        if(contentTypeId != 0) {
            urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentTypeId) , "UTF-8"));
        }
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(String.valueOf(areaCode) , "UTF-8"));
        if(categoryParentLv != 0) {
            urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode(String.valueOf(sigunguCode) , "UTF-8"));
        }

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
        BufferedReader br;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        br.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        String areaBasedList = sb.toString();

        return areaBasedList;
    }

}
