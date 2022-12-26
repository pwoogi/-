## Flow Chart

1. 회원가입


<img src="https://user-images.githubusercontent.com/107388110/209534571-1b79e7ee-785e-418f-b59b-223a857ef0a1.PNG">

2. 로그인

<img src="https://user-images.githubusercontent.com/107388110/209534606-ac58ba42-333b-4abf-b6e0-ed1595fbef34.PNG">

3. 포스트 작성

<img src="https://user-images.githubusercontent.com/107388110/209534652-d0751b00-f224-4096-ab54-9ce8e67deb60.PNG">

4. 포스트 삭제

<img src="https://user-images.githubusercontent.com/107388110/209534677-87330719-f807-4240-8de8-fbe445b2c1df.PNG">

5. 포스트 수정

<img src="https://user-images.githubusercontent.com/107388110/209534719-1de274bb-40cd-4974-b600-49cd3d2c522d.PNG">

6. 피드 목록

<img src="https://user-images.githubusercontent.com/107388110/209534757-0099a86e-1ab0-468c-ba9d-d24433330bdf.PNG">

7. 좋아요 기능 : User A가 B 게시물에 좋아요를 누른 상황

<img src="https://user-images.githubusercontent.com/107388110/209534807-4158843e-94e5-4cbb-8ed8-f6a0c5340604.PNG">


8. 댓글 기능 : User A가 B 게시물에 댓글을 남긴 상황

```mermaid
sequenceDiagram
autonumber
client ->> server: 댓글 작성
    alt 성공한 경우
    server ->> db : db update 요청
    db ->> server : 성공 반환
    server->>client: 성공 반환
    else 로그인하지 않은 경우
    server->>client: reason code와 함께 실패 반환
    
    else db 에러
    server ->> db : db update 요청
    db ->> server : 에러 반환
    server->>client: reason code와 함께 실패 반환
    
    else B 게시물이 존재하지 않는 경우 
    server ->> db : db update 요청
    db ->> server : 에러 반환
    server->>client: reason code와 함께 실패 반환
    
    else 내부 에러
    server->>client: reason code와 함께 실패 반환
    end
```

```mermaid
  sequenceDiagram
    autonumber
    client ->> server: 댓글 작성
    alt 성공한 경우 
    server ->> db : 좋아요 누를 수 있는 조건 체크 
    db -->> server : 응답 
    server --) kafka : event produce request
    server->>client: 성공 반환
    kafka --) server : event consume 
    server ->> db : db update 요청
    db -->> server : 성공 반환 
    
    else 로그인하지 않은 경우
    server->>client: reason code와 함께 실패 반환
    
    else db 에러
    server ->> db : 댓글 작성 조건 체크 
    db -->> server : 응답 
    server --) kafka : event produce request
    server->>client: 성공 반환
    kafka --) server : event consume 
    loop db update 실패시 최대 3회 재시도 한다
        server ->> db : db update 요청
    end
    db -->> server : 실패 반환 
    
    else B 게시물이 존재하지 않는 경우 
    server ->> db : 댓글 작성 조건 체크 
    db ->> server : 에러 반환
    server->>client: reason code와 함께 실패 반환
    
    else 내부 에러
    server->>client: reason code와 함께 실패 반환
    end
```

9. 알람 기능 : User A의 알람 목록에 대한 요청을 한 상황

```mermaid
sequenceDiagram
autonumber
client ->> server: 알람 목록 요청 
    alt 성공한 경우
    server ->> db : db query 요청
    db ->> server : 성공 반환
    server->>client: 성공 반환
    else 로그인하지 않은 경우
    server->>client: reason code와 함께 실패 반환
    
    else db 에러
    server ->> db : db query 요청
    db ->> server : 에러 반환
    server->>client: reason code와 함께 실패 반환
    else 내부 에러
    server->>client: reason code와 함께 실패 반환
    end
```