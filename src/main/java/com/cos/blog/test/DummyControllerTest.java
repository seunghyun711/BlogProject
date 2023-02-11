package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository; // 처음 상태는 null
    @PostMapping("/dummy/join")
    public String join(User user){ // 매개변수 3개는 key-value형태의 데이터를 받는다.
        System.out.println("id : " + user.getId());
        System.out.println("userName : " + user.getUserName());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        userRepository.save(user);
        return "회원가입 완료";

    }
}
