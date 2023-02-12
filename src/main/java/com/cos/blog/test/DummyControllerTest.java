package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Supplier;

// data를 리턴해주는 컨트롤러 RestController
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository; // 처음 상태는 null

    //{id}주소로 파리미터를 전달 받을 수 있다.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // user/4를 보내서 못찾은 경우 null 리턴 이걸 처리하기 위해 Optional로 user객체를 래핑하여 가져옴
        // 람다식 이용
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 user는 없습니다. id : " + id);
            }
        });
        // 요청 : 웹브라우저
        // user 객체 -> 자바 객체
        // user객체를 웹브라우저가 이해할 수 있는 데이터 json으로 변환해야 한다.
        /*
         스프링 부트는 MessageConverter가 응답시에 자바 객체를 리턴하면 MessageConverter가 Jasckson라이브러리를 통해
         user객체를 json으로 변환하여 브라우저에게 던진다.
         */
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){ // 매개변수 3개는 key-value형태의 데이터를 받는다.
        System.out.println("id : " + user.getId());
        System.out.println("userName : " + user.getUserName());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";

    }
}
