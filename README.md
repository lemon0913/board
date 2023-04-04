# Yoshi

전반적인 웹의 기본 소양이 되는 프로젝트

게시물과 댓글을 통해 스시 오마카세 정보를 공유할 수 있는 웹 사이트



# 목차

- 프로젝트 소개

  - 프로젝트 개요

  - 사용 기술
  - 실행 화면

- 구조 및 설계

  - 패키지 구조
  - DB 설계
  - API 설계

- 개발 내용

- 마치며

  - 프로젝트 보완사항
  - 후기



# 프로젝트 소개

### 1. 프로젝트 개요

웹 프로그래밍의 기본 소양인 게시판을 만들며 자바 웹 프로그래밍을 배우고자 시작하게 된 프로젝트입니다.

스스로 독학하며 제작한 개인 프로젝트이기 때문에 부족한 부분이 많으나 추후 공부를 계속 진행하며 보완해나갈 예정입니다. 

- __프로젝트 명칭__ : Yoshi(요즘 스시)
- __프로젝트 소개__ : 쉽게 정보를 얻기 어려운 오마카세에 대한 정보를 공유할 수 있는 웹 사이트
- __개발 인원__ : 1명(서정화)
- __개발 기간__ : 2022.07.29 ~ 
- __주요 기능__:
  - __게시판__ : CRUD 기능, 조회수, 페이징 및 검색 처리, 네이버API를 활용하여 지도 보여주기
  - __사용자__ : Security 회원가입 및 로그인, 회원 정보 수정, 회원가입 시 유효성 검사 및 중복 검사
  - __댓글__ : CRUD 기능



### 2. 사용 기술

#### 백엔드

- __백엔드 개발 환경__

  - Window
  - IntelliJ

  - Java 11
  - SpringBoot 2.5.6
  - JPA(Spring Data JPA)
  - Spring Security

- __Build Tool__ 

  - Gradle 7.5

- __DataBase__

  - MariaDB

    

#### 프론트엔드

- __프론트 개발 환경 및 언어__
  - HTML
  - CSS
  - JavaScript
  - Mustache
  - Bootstrap



#### 배포 환경

- AWS
  - EC2
  - RDS



### 3. 실행 화면

#### 게시글 관련

- __게시글 전체 목록__
  ![게시글 전체 목록1](https://user-images.githubusercontent.com/86991963/229799581-8a562cb5-25c7-4fcf-b83e-03feb9a644c3.PNG)
  ![게시글 전체 목록2](https://user-images.githubusercontent.com/86991963/229800008-d7605154-8f22-460f-b3f0-0e77d6be893f.PNG)
  게시글 전체 목록을 페이징 처리하여 조회 가능

  페이징은 게시글 10개를 기준으로 일어나며 다음 페이지가 없을 경우 페이징 버튼 비활성화

- __게시글 등록__
  ![게시글 등록](https://user-images.githubusercontent.com/86991963/229800149-c3f86dcf-2a02-4cd8-b410-0049810e9f35.PNG)

  로그인 한 사용자말 게시글 등록이 가능하며 작성 후 목록 화면으로 돌아감

- __게시글 상세보기__

  ![게시글 상세보기(본인 작성 아님)](https://user-images.githubusercontent.com/86991963/229800373-992525d8-0333-47bb-8529-9472caa1cf1b.PNG)

  ![게시글 상세보기(본인 작성)](https://user-images.githubusercontent.com/86991963/229800486-f8f1556b-68c1-4e31-93c1-2e08cf6e696d.PNG)

  게시글 상세 보기 페이지

  본인이 작성한 글인 경우에만 수정 및 삭제 버튼 활성화

- __게시글 수정__

  ![게시글 수정](https://user-images.githubusercontent.com/86991963/229800594-8dfb5516-2609-426f-a4a7-25c329cd9e7c.PNG)

  글 번호 및 작성자는 수정 불가

  본인이 작성한 글인 경우에만 수정 가능

  수정 후 완료버튼을 누르면 상세 보기 화면으로 돌아가고 취소 버튼을 눌러도 상세보기 화면으로 돌아감

- __게시글 삭제__

  Confirm으로 삭제할지 확인하고, 삭제 후 전체 목록 화면으로 돌아감

- __게시글 검색__

  ![게시글 검색(가게명)](https://user-images.githubusercontent.com/86991963/229800847-4958a36a-62d7-44d4-b6eb-8b7e4c1a7c87.PNG)

  ![게시글 검색(위치)](https://user-images.githubusercontent.com/86991963/229800919-4bb15022-9a47-4b51-8bc4-2c080a793314.PNG)

  검색 키워드에 포함된 글을 모두 보여줌

  가게명과 주소로 검색 가능

  검색된 게시글이 많을 경우 다음과 같이 페이징 처리되어 조회 가능



#### 회원 관련

- __회원가입__

  ![회원가입](https://user-images.githubusercontent.com/86991963/229801131-16ccf26d-e021-4a02-a2cc-3ba19950fdf6.PNG)

  ![회원가입(가입 양식을 지키지 않음)](https://user-images.githubusercontent.com/86991963/229801198-d21dda35-5020-416d-9906-05d34104ec21.PNG)
  
  ![회원가입(중복 가입)](https://user-images.githubusercontent.com/86991963/229801306-e1c9cc0f-3a17-4e85-8588-f541909db1d9.PNG)

  회원가입 시 유효성 검사 및 중복확인을 진행하며 완료시 회원 정보를 저장하고 로그인 화면으로 이동

- __로그인__

  ![로그인](https://user-images.githubusercontent.com/86991963/229801442-b58e67a2-851c-4513-b638-b827c3759a1f.PNG)

  ![로그인(로그인 실패)](https://user-images.githubusercontent.com/86991963/229801548-f3d2fed7-1c15-485b-b910-3936221c8508.PNG)

  로그인 실패시 어떤 이유로 실패한건지 메시지가 나오고 로그인에 성공하면 게시글 전체 화면으로 돌아감

- __회원정보 수정__

  ![회원 정보 수정](https://user-images.githubusercontent.com/86991963/229801616-b808f078-0e43-4790-ad5a-1b8328afe17b.PNG)

  닉네임과 비밀번호만 변경 가능하며 이미 사용중인 닉네임인 경우 현재 사용 중임을 알려주고 수정 완료시 게시글 전체 목록 화면으로 돌아감



#### 댓글 관련

- __댓글 작성 화면__

  ![댓글 작성(로그인 안함)](https://user-images.githubusercontent.com/86991963/229803330-175411d4-de7f-4d23-9dc6-c4fe4827d18a.PNG)

  ![댓글 작성(로그인 함)](https://user-images.githubusercontent.com/86991963/229803423-f4936989-92fd-4ae0-8a6d-2a3f30998ab7.PNG)

  로그인하지 않은 사용자의 경우 로그인을 해야 댓글을 등록할 수 있다는 메시지가 나옴

  로그인한 사용자의 경우 댓글 작성이 가능하며 댓글 작성시 현재 페이지를 reload함

- __댓글 수정__

  ![댓글 수정(본인 작성 아님)](https://user-images.githubusercontent.com/86991963/229803890-1d24ca7d-ba7e-4c10-8969-b3ba3f7ce288.PNG)

  ![댓글 수정(본인 작성)](https://user-images.githubusercontent.com/86991963/229804028-29a417ce-5b2e-48ed-90ad-691c1e7fd029.PNG)

  본인이 작성한 댓글인 경우에만 수정 가능

  수정 완료 후 현재 페이지를 reload 함

- __댓글 삭제__

  댓글 작성자만 삭제 가능

  삭제 완료 후 현재 페이지를 reload함





# 구조 및 설계

### 1. 패키지 구조

```
├─main
│  ├─java
│  │  └─com
│  │      └─kiki
│  │          └─board
│  │              ├─application
│  │              │  ├─dto
│  │              │  │  ├─CommentDto.java
│  │              │  │  ├─PostsDto.java
│  │              │  │  └─UserDto.java
│  │              │  ├─security
│  │              │  │  └─auth
│  │              │  │       ├─CustomAuthFailureHandler.java
│  │              │  │       ├─CustomUserDetails.java
│  │              │  │       └─CustomUserDetailsService.java
│  │              │  └─validator
│  │              │       ├─CommentService.java
│  │              │       ├─PostsService.java
│  │              │       └─UserService.java
│  │              ├─domain
│  │              │  ├─BaseTimeEntity.java
│  │              │  ├─Comment.java
│  │              │  ├─Posts.java
│  │              │  ├─Role.java
│  │              │  └─User.java
│  │              ├─infrastructure
│  │              │  ├─config
│  │              │  │       └─SecurityConfig.java
│  │              │  └─persistence
│  │              │            ├─CommentRepository.java
│  │              │            ├─PostsRepository.java
│  │              │            └─UserRepository.java
│  │              ├─presentation
│  │              │  ├─CommentApiController.java
│  │              │  ├─PostIndexController.java
│  │              │  ├─PostsApiController.java
│  │              │  ├─UserApiController.java
│  │              │  └─UserIndexController.java
│  │              └─BoardApplication.java
│  └─resources
│      ├─static
│      │  ├─assets
│      │  │  └─img
│      │  └─js
│      │      └─app
│      │           └─Index.js
│      ├─templates
│      │  ├─comment
│      │  │  ├─form.mustahce
│      │  │  └─list.mustahce
│      │  ├─layout
│      │  │  ├─footer.mustahce
│      │  │  └─header.mustahce
│      │  ├─user
│      │  │  ├─user-join.mustahce
│      │  │  ├─user-login.mustahce
│      │  │  └─user-modify.mustahce
│      │  ├─index.mustache
│      │  ├─posts-read.mustache
│      │  ├─posts-search.mustache
│      │  ├─posts-update.mustache
│      │  └─posts-write.mustache
│      ├─application.properties
│      └─application-real.properties
└─test
    └─java
        └─com
            └─kiki
                └─board
```



### 2. DB 설계

![erd](https://user-images.githubusercontent.com/86991963/229805197-8a0d1a48-7aac-4236-8935-c0fcc42cab91.png)



### 3. API 설계

#### 게시글 관련 API

| 기능                    | Method | URL                                         | Return Page                          |
| ----------------------- | ------ | ------------------------------------------- | ------------------------------------ |
| 게시글 전체 목록 조회   | GET    | /                                           | 게시글 전체 목록                     |
| 게시글 페이징 목록 조회 | GET    | /?page={번호}                               | 게시글 페이징 목록                   |
| 게시글 상세보기         | GET    | /posts/read/{id}                            | 게시글 상세보기 페이지로 이동        |
| 게시글 등록 페이지      | GET    | /posts/write                                | 게시글 등록 페이지로 이동            |
| 게시글 수정 페이지      | GET    | /posts/update/{id}                          | 게시글 수정 페이지로 이동            |
| 게시글 검색             | GET    | /posts/search?keyword={keyword}             | 게시글 검색                          |
| 게시글 검색 후 페이징   | GET    | /posts/search?keyword={keyword}&page={번호} | 게시글 검색 후 페이징 목록           |
| 게시글 등록             | POST   | /api/posts                                  | 글 등록 후 목록 페이지 이동          |
| 게시글 조회             | GET    | /api/posts/{id}                             | 게시글 조회                          |
| 게시글 수정             | PUT    | /api/posts/{id}                             | 게시글 수정 후 상세보기 페이지       |
| 게시글 삭제             | DELETE | /api/posts/{id}                             | 게시글 삭제 후 전체 목록 페이지 이동 |



#### 회원 관련 API

| 기능            | Method | URL            | Return Page               |
| --------------- | ------ | -------------- | ------------------------- |
| 회원가입 페이지 | GET    | /auth/join     | 회원가입 페이지로 이동    |
| 회원가입        | POST   | /auth/joinProc | 회원가입 후 로그인 페이지 |
| 로그인 페이지   | GET    | /auth/login    | 로그인 페이지로 이동      |
| 로그아웃        | GET    | /logout        | 로그아웃 후 목록 페이지   |



#### 댓글 관련 API

| 기능                | Method | URL                           |
| ------------------- | ------ | ----------------------------- |
| 댓글 전체 목록 조회 | GET    | /api/posts/{id}/comments      |
| 댓글 등록           | POST   | /api/posts/{id}/comments      |
| 댓글 수정           | PUT    | /api/posts/{id}/comments/{id} |
| 댓글 삭제           | DELETE | /api/posts/{id}/comments/{id} |



# 개발 내용

책과 개인 velog 참고



# 마치며

### 1. 프로젝트 보완사항

첫 springboot 웹 개발 프로젝트였기 때문에 프론트엔드 부분에 신경을 쓸 겨를이 없어 템플릿 엔진으로 Mustache를 선택하였다. 그 덕에 간단하게 프론트엔드 개발이 가능했지만 기본 CRUD 기능 이상으로 다른 기능을 추가하려는 과정에서 mustache는 로직을 넣을 수 없다는 단점에 의해 제약사항이 많았다. 후에 프로젝트를 진행할 기회가 생긴다면 Thymeleaf를 사용하거나 아니면 현재 프로젝트에서 브랜치를 나눠 Mustache로 구현한 부분들을 Thymeleaf로 바꿔가고 싶다.



추가적인 공부를 통해 다음과 같은 기능을 추가할 예정이다

- 파일 업로드 기능
- 좋아요 기능
- 검색 결과 필터링 기능(댓글 많은 순, 조회수 많은 순...)



### 2. 후기

Java와 Spring을 배운 이후 처음으로 진행한 웹 개발 프로젝트였기 때문에 아쉬운 부분이 많으면서도 뿌듯한 프로젝트였다. 처음에는 과연 내가 프로젝트를 끝낼 수 있을까라는 두려움이 컸지만 생각보다 많은 자료가 검색가능해 프로젝트를 마칠 수 있었던 것 같다. 개발하면서 발생한 많은 오류들도 다행히 검색으로 긴 시간을 들이지 않고 해결 가능했다.(오히려 Django로 웹 개발할 때 보다 해결이 빨랐던 것 같다..spring으로 웹 개발을 하는 경우가 더 많아서 그런것 같다.)  기본 CRUD 기능 외의 추가적인 기능들은 좀 더 공부를 해야 구현이 가능할 것 같다.



프로젝트 진행과정에서 아쉬웠던 부분은 우선 발생한 오류의 대부분이 내가 참고한 교재와 springboot와 gradle의 버전이 달라서 발생한 점이었다는 것이다. 참고자료를 기반으로 개발을 한다면 저자와 버전을 잘 맞춰서 개발하도록 하자. 

그리고 개발보다도 aws에 프로젝트를 배포하는것이 더 어려운것 같았다. 개발과정에서 발생한 오류는 검색을 통해 오류 발생 원인 파악이 가능했는데 배포 과정에서 발생한 오류는 해결되어도 당최! 어떻게 해결한 건지 알 수가 없었다. 그래서 현재 배포까지만 하고 CI/CD와 무중단 배포까지는 진행하지 못한 상태이다. springboot 공부를 좀 더 진행한 후에 aws와 관련된 공부도 꼭 진행해야겠다. 

마지막으로 Java 공부를 다시 좀 더 꼼꼼하게 할 필요성을 느꼈다. 확실히 python-Django 조합으로 웹 개발을 했을 때보다 작성한 코드를 이해하는데 시간이 더 걸렸던 것 같다.

