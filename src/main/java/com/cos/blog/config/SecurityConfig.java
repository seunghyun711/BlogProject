package com.cos.blog.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 해당 클래스를 빈으로 등록
@EnableWebSecurity // 시큐리티 필터가 등록이 된다. -> 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증을 미리 체크한다는 의미
public class SecurityConfig{
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/**") // /auth 경로로 들어오는 모든 경로는 허용
                .permitAll()
                .anyRequest()// 그게 아니면
                .authenticated() // 다른 요청은 인증이 되어야 한다.
                .and()// 요청 경로가 /auth가 아닌 /로 들어요면 자동으로 /auth/loginFormd으로 가게 커스텀
                .formLogin()
                .loginPage("/auth/loginForm");
        return http.build();

    }
}
