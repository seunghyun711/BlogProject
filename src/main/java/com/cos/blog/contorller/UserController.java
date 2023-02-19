package com.cos.blog.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 이걸로만 허용
// 그냥 주소가 /이면 index.html 허용
// static이하에 있는 /js/**, /css/**

@Controller
public class UserController {

    @GetMapping("/auth/joinForm") // 가입 폼으로 이동
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm") // 로그인 폼으로 이동
    public String loginForm(){

        return "user/loginForm";
    }
}
