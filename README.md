# **Korea Tourism(Kotu)**
 사용자의 GPS 위치를 기반으로 한국관광공사 API를 활용해 주변 관광지, 음식점, 숙박시설 등 다양한 정보를 제공하는 서비스입니다. 이 서비스를 통해 사용자는 실시간으로 주변의 관광 명소와 관련된 유용한 정보를 쉽게 탐색할 수 있습니다.

## 목차
- 사용 기술
- 프로젝트 구조
- ERD 구조
- 구현한 기능
- 프로젝트를 진행하면서 발생한 이슈 및 고민과 해결 과정

## 사용 기술
**Backend**
- Java 17
- Spring boot 3
- Spring Security6

**Frontend**
- Thymeleaf
- JavaScript
- HTML
- CSS 

**DB**
- AWS RDS(Relational Database Service)(MySQL)
- Mybatis3

**API**
- Google Maps API
- 한국관광공사 Tour API

**형상관리**
- GitHub

**배포(CI/CD)**
- Github Actions
- Docker
- AWS ECR(Elastic Container Registry)
- AWS EB(Elastic Beanstalk)
- AWS CM(Certificate Manager)
- AWS Route 53

## 프로젝트 구조

Github Actions → Docker 이미지화 → AWS ECR → AWS Elastic Beanstalk, RDS → Route 53 (client)
![project_architecture.png](https://github.com/LooklikeDinosour/KoreaTourProject/blob/kotu/project_architecture.png)

## ERD 구조
![project_erd.png](https://github.com/LooklikeDinosour/KoreaTourProject/blob/kotu/project_erd.png)


## 구현한 기능
- **Spring Security를 활용한 회원가입 및 로그인 기능 구현**   
  회원가입 시 BCryptPasswordEncoder를 사용하여 비밀번호를 암호화한 후 데이터베이스에 안전하게 저장함으로써 보안성을 강화하였습니다.
- **한국관광공사 API 및 Google Maps API를 통해 정보 제공**   
  한국관광공사 API를 활용하여 사용자의 현재 위치 주변 관광 정보를 제공하고, Google Maps API를 통해 해당 정보를 지도상에 시각화하여 경도와 위도를 기반으로 위치를 쉽게 확인할 수 있도록 구현하였습니다.
- **CI/CD를 통해 효율적인 배포 구축**   
  GitHub Actions, Docker, AWS ECR, 그리고 AWS Elastic Beanstalk를 활용하여 지속적인 통합(CI)과 배포(CD) 파이프라인을 구축하였습니다. 이를 통해 배포 과정을 자동화하여 신속하고 일관된 배포가 가능하도록 최적화했습니다.

## 프로젝트를 진행하면서 발생한 이슈 및 고민과 해결 과정

- JSON 역직렬화 과정에서 다양한 DTO 클래스 처리로 인한 코드 중복이 발생했습니다. 이를 해결하기 위해 제네릭을 활용한 단일 메서드를 구현하여 중복을 제거하고 확장성을 개선했습니다.

- POST 요청이 필요한 페이지에서 CSRF 토큰 태그를 누락해 에러가 발생하는 경우가 있었습니다. 이를 해결하기 위해 SecurityConfig에서 GET 메소드를 제외한 모든 HTTP 요청에 대해 CSRF 토큰이 자동으로 적용되도록 설정했습니다. 그 결과, POST 요청이 필요한 페이지를 작성할 때, CSRF 토큰 태그 누락으로 인한 실수를 줄이고 오류 발생 가능성을 크게 감소시켰습니다.

- View 메뉴 구성시 중복되는 작업이 발생하여 이를 줄이고자 Java의 Enum을 활용하여 View에서 메뉴 구성을 구현했습니다. 이를 통해 프론트엔드 작업이 더욱 편리해졌으며, Enum이 제공하는 데이터의 안정성 덕분에 오타로 인한 오류를 줄이고 작업 효율성을 높일 수 있었습니다. 또한, API와의 통신에서 명확성을 확보할 수 있었습니다.

- 배포 후 발생한 Mybatis의 BindingException을 해결하기 위해 다양한 방법을 시도했습니다. 테스트 도입, 환경 변수 설정 시 개발(local) 환경과 배포 환경의 properties 파일을 구분하는 방법 등을 적용하면서, 배포를 직접 경험하지 않았다면 알기 어려웠던 여러 고려사항을 배우게 되었습니다.
