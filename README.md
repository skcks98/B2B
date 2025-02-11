
<h1>modeunTicket </h1>
📍 프로젝트 기간 : 2024.11.05 ~ 2024.12.02
<br>
<br>


| ![image](https://github.com/user-attachments/assets/371da412-cfee-4dbd-af87-f96734ba56d3)| ![image](https://github.com/user-attachments/assets/f804c099-d21b-415f-ac9e-3845dd882025)|
| --- | --- |

|![image](https://github.com/user-attachments/assets/d21b55cc-e172-4758-b7dc-fa8ed27dea70)| ![image](https://github.com/user-attachments/assets/a7473de6-730e-4f89-a4d4-a5ae8441f96c)|
| --- | --- |

|![image](https://github.com/user-attachments/assets/3e1ab179-6e46-481e-be4d-162d31ed61f0)|![image](https://github.com/user-attachments/assets/48bc6653-928c-4c19-b450-332e344ed6ba)|
| --- | --- |


## 📌 개요

```

 🎪 모든티켓은 인터파크를 벤치마킹하여 구현한 티켓팅 홈페이지입니다. 

 🎻 서비스 사용자들은 주요 연극, 클래식, 뮤지컬의 티켓 예매를 제공하며 직관적인 UI와 빠른 예매 프로세스를 특징으로 합니다.
     또한 관리자 페이지 외에 실제 공연을 등록하고 수정할 수 있는 공연관리자 페이지를 추가해 공연 관리 기능을 제공합니다.

 ✍ 벤치마킹 포인트
    - 예매 과정의 직관성
    - 공연 정보 및 리뷰 제공 방식
    - 사용자 경험 개선을 위한 실시간 좌석 선택 기능

 💻 사용자와 공연 관리자에게 효율적이고, 편리한 환경을 제공하는 플랫폼입니다. 
    - 사용자 : 공연 정보 조회, 좌석 선택, 결제 기능 제공
    - 공연 관리자 : 공연 등록 및 수정 등 다양한 관리 기능 제공
    - 웹 페이지 관리자 : 사용자 관리, 공연장 관리, 예매 좌석 관리 등
    다양한 관리 기능을 활용할 수 있습니다.

  🪪 해당 홈페이지는 회원가입을 해야 이용할 수 있도록 구현했습니다.
```

## 개발 팀 소개

<table>
  <tr height="205px">
    <td align="center" width="200px">
      <a href="https://github.com/Jung-Dae-seung"><img src="https://github.com/user-attachments/assets/4c678845-d11f-411c-95db-603d01ab958a"/>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/skcks98"><img src="https://github.com/user-attachments/assets/4c678845-d11f-411c-95db-603d01ab958a"/></a>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/skcks98"><img src="https://github.com/user-attachments/assets/4c678845-d11f-411c-95db-603d01ab958a"/></a>
    </td>
    
  </tr>
  <tr>
    <td align="center" width="200px">
      <a href="https://github.com/Jung-Dae-seung/"><strong>정대승</strong></a><br>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/skcks98/"><strong>나찬웅</strong></a><br>
    </td>
    <td align="center" width="200px">
      <strong>민경제</strong><br>
    </td>
  </tr>
</table>

<br />


## 📊 ERD
![image](https://github.com/user-attachments/assets/5596b45a-9111-47bd-853f-32b8552dae9e)





## 📂 폴더 구조

```
📦src
 ├─ 📂main/java/
 │  ├─ 📂edu/kh/project
 │  │   ├─ 📂common
 │  │   │  ├─ 📂config
 │  │   │  ├─ 📂filter
 │  │   │  ├─ 📂jwt
 │  │   │  └─ 📂util
 │  │   ├─ 📂email
 │  │   ├─ 📂main
 │  │   ├─ 📂member
 │  │   ├─ 📂myPage
 │  │   ├─ 📂notice
 │  │   ├─ 📂payment
 │  │   ├─ 📂perfmgr
 │  │   ├─ 📂performance
 │  │   ├─ 📂redis
 │  │   ├─ 📂search
 │  │   ├─ 📂statistics
 │  └─ 📂resources
 │     ├─ 📂mappers
 │     ├─ 📂static
 │     ├─ 📂css
 │     │  ├─ 📂common
 │     │  ├─ 📂myPage
 │     │  ├─ 📂notice
 │     │  ├─ 📂payment
 │     │  ├─ 📂perfmgr
 │     │  └─ 📂performance
 │     ├─📂images
 │     ├─ 📂js
 │     │  ├─ 📂common
 │     │  ├─ 📂myPage
 │     │  ├─ 📂notice
 │     │  ├─ 📂payment
 │     │  ├─ 📂perfmgr
 │     │  └─ 📂performance
 │     ├─ 📂templates
 │     │  ├─ 📂common
 │     │  │  └─ 📂modals
 │     │  ├─ 📂email
 │     │  ├─ 📂header
 │     │  ├─ 📂mypage
 │     │  ├─ 📂notice
 │     │  ├─ 📂payment
 │     │  ├─ 📂perfmgr
 │     │  ├─ 📂performance
 │     │  └─ 📂sideMenu
 │     ├─ 📜application.properties
 │     ├─ 📜config.properties
 │     ├─ 📜logback-spring.xml
 │     ├─ 📜messages.properties
 │     └─ 📜mybatis-config.xml
```
