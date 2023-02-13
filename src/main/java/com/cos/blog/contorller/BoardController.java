package com.cos.blog.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/blog")
    public String index(){
        return "views/index";
    }
}
