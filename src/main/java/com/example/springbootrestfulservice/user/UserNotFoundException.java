package com.example.springbootrestfulservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//hTTP status code
//2XX -> OK (정상)
//4XX -> Client가 잘못했을때 (잘못된 리소스 호출 등)
//5XX -> Server상의 오류
@ResponseStatus(HttpStatus.NOT_FOUND)   //4XX code 발생
public class UserNotFoundException extends RuntimeException {//실행시에 발생하는 오류
    public UserNotFoundException(String message) {
        super(message);
    }
}
