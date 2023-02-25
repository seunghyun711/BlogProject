package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 해당 클래스를 빈으로 등록
@EnableWebSecurity // 시큐리티 필터가 등록이 된다. -> 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증을 미리 체크한다는 의미
public class SecurityConfig{
    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception{
        return ac.getAuthenticationManager();
    }


    @Bean // 스프링 빈 등록
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    /*
     시큐리티가 대신 로그인해주는데 password를 가로채기 하는데
     해당 password가 어떤 걸로 해시가 되어 회원가입이 되었는지 알아야 같은 해시로 암호화하여 db에 있는 해시랑 비교할 수 있다.
     */

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ // 이게 없으면 패스워드 비교를 못함
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/*") // /auth 경로로 들어오는 모든 경로는 허용
                .permitAll()
                .anyRequest()// 그게 아니면
                .authenticated() // 다른 요청은 인증이 되어야 한다.
                .and()// 요청 경로가 /auth가 아닌 /로 들어요면 자동으로 /auth/loginFormd으로 가게 커스텀
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인한다.
                .defaultSuccessUrl("/"); // 정상적으로 요청 완료시 /경로로 이동
        return http.build();

    }
}
