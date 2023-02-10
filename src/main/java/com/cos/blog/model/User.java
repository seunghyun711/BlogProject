package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 해당 클래스가 mysql에 테이블로 생성
@Table(name = "user")
public class User {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 디비(mysql)의 넘버링 전략을 따른다.
    private Long id; // 고유 번호

//    @Column(nullable = false) // 해당 열은 널값이 들어가면 안된다.
    private String userName; // 유저의 아이디

//    @Column(nullable = false, length = 100) // 패스워드 길이는 100으로 제한
    private String password; // 유저의 비밀번호(auto_increment)

//    @Column(nullable = false, length = 50)
    private String email; // 유저의 이메일

//    @ColumnDefault("'user'")  // 기본은 user
    private String role; // Enum을 쓰는 것이 좋다. / admin, user, manager등의 권한 관리

//    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDates; // 시간
}
