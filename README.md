# **게임 커뮤니티 **GameDuos** Ver.1.0 Beta** <br>
[![Build Status](https://travis-ci.org/matamong/spring-boot2-web-service.svg?branch=master)](https://travis-ci.org/matamong/spring-boot2-web-service)
![GitHub language count](https://img.shields.io/github/languages/count/matamong/spring-boot2-web-service)
![spring boot version](https://img.shields.io/badge/Spring--Boot%20-v2.1.7.RELEASE-brightgreen)
![Amazone Linux version](https://img.shields.io/badge/Amazon%20Linux%20AMI-2018.03.0-orange)

<br><br>

## **GameDuos**
게임을 사랑하는 다양한 사람들이 편안하게 만나고 소통하는 공간의 장을 지향합니다.

<br><br>



## <1차 배포> 🎊 **포함 된 기능** 

- 소셜 로그인 (OAuth 2.0)
    - **Google 지원**
    - Facebook 지원 예정 (개인 정보 약관 / https 작업 중)
    - Kakaotalk 지원 예정 (개발 마무리 중)
- 자유 게시판
- 댓글 
    - 대댓글 포함 x
- 추천코드&인증 제도
    - 추천코드를 통하여 인증 된 회원들만 USER 권한을 얻어 활동할 수 있습니다.
    - 글과 댓글을 작성하여 포인트를 모으고 추천코드를 발급하여 같이 놀고 싶은 친구를 초대해주세요!
    - 유저 권한 및 접근 제한(MASTER, ADMIN, USER, GUEST)
- OW 심리테스트 
- 포인트 (게시판 포인트, 댓글 포인트) 
- 마이 페이지 (정보 수정, 인증코드 발급 및 사용, 포인트 확인, 회원 탈퇴) 
- 관리자 페이지(전체 유저 리스트 조회, 유저 조회, 유저 포인트 부여, 인증코드 조회, 권한 부여)
- `new` 면접용 권한 추가(자동 인증된 회원이며 관리자페이지 접근가능하지만 관리자api는 접근불가) 

<br>
미완성 디자인 버전 적용

## <1차 배포> 🎊 **기능 자세히 보기**

- OAuth 2.0
   - Google, 소셜 로그인 지원
   - Spring Boot 의 OAuth2.0 기능을 이용
   - DB에 저장되어 있는 유저는 기존의 정보를 그대로 사용
- Spring Security
    - Role 권한 부여 : MASTER / ADMIN / USER / GUEST 
    - 첫 로그인 시 OAuth2 의 토큰을 받아서 유저 인증(Authentication) / 세션저장
    - Role 에 따라 인가(Authorization)
    - 추천코드로 인증 시 Role 업데이트 및 권한주기
    - csrf() 공격 방지
- 주 요청은 Ajax를 이용하여 REST 방식을 최대한 사용하려고 하였습니다.
    - hateoas 미적용 등등 완벽하진 않습니다.
- OW 심리테스트
    - JQuery / JavaScript 를 최대한 이용하여 서버 측에서 연산을 안하도록 유도하였습니다.. 

<br><br>

- 버전관리(Git)
    - master / develop 브랜치 이용
    
<br><br>

- https 적용
 
## 개발 환경
- Java8
- IDE
    - IntelliJ
    - Visual Studio Code 
- Gradle
    - Gradle 6.0.1
- Spring Boot
    - v2.1.7.RELEASE
- template
    - thymeleaf
- JPA
- REST API
- DB
    - Maria DB
    - h2
- AWS EC2
    -  Amazon Linux AMI - 2018.03.0
- AWS RDS
    - Maria DB
- Travis CI 빌드  
- AWS Code Deploy 배포
- AWS S3 파일 저장
- NginX 무중단 배포 적용
<br><br>
- JavaScript / Jquery / Ajax
- semantic-ui.css 이용
- 구글 애널리틱스 사용

