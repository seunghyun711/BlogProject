package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service // 스프링이 컴포넌트 스캔을 통해 스프링 빈에 등록함 -> ioc해준다.
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional // 회원가입 메서드가 하나의 트랜잭션으로 묶임, 전체가 성공하면 커밋이 될 것이고, 실패 시 롤백이 될 것이다.
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해시
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);

    }

}
