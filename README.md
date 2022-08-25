1. 소스 실행 방법

- terminal/cmd 창에서 하기 명령어로 소스(경로 : build > libs > test-ably-api-0.0.1-SNAPSHOT.jar) 실행 
>> java -jar -Dspring.profiles.active=local test-ably-api-0.0.1-SNAPSHOT.jar

2. API 사용방법
   - 회원가입 : POST http://localhost:8070/api/users
   Body Data Sample(raw JSON)
     {
       "username": "아이디",
       "password": "비밀번호",
       "realname": "실명",
       "nickname": "별명",
       "email": "이메일",
       "phone": "전화번호",
       "roles": [
         "ROLE_EMPLOYEE"
       ]
     }
