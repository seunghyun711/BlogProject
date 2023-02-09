package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청에 대한 데이터 응답을 해주는 컨트롤러 -> RestController
@RestController
public class HttpControllerTest {
    // get,post,put,delete 테스트

    // 브라우저로 요청하는 것은 get만 가능하다 나머지는 postman 같은 툴로 확인해야 한다.
    // http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(Member m) { // http://localhost:8080/http/get?id=1 으로 요청을 보내면 @RequestParam으로 1을 받는다.
        return "get 요청 : " + m.getId() + m.getUsername();
    }

    // http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest() {
        return "post 요청";
    }

    // http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    // http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
