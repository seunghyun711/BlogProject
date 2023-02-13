package com.cos.blog.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/blog/user/joinForm") // 가입 폼으로 이동
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/blog/user/loginForm") // 로그인 폼으로 이동
    public String loginForm(){

        return "user/loginForm";
    }
}
