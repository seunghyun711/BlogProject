package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // Exception이 발생 시 이 클래스로 온다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class) // IllegalArgumentException 발생 시 에러를 해당 함수에 전달
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
