package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 빈 등록
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /*
     스프링이 로그인 요청을 가로챌 때 userName, password 2개 가로챈다.
     password 부분 처리는 알아서 함
     해당 username이 db에 있는지만 확인하면 된다.
     */
    @Override // 해당 메서드를 만들어야 user담아서 보낼 수 있다. 이게 없으면 id : user, password : 스프링 실행시 콘솔 창 비밀번호가 사용된다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).
                orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장된다.
    }
}
