package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 해당 클래스가 mysql에 테이블로 생성
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더 패턴
@Table(name = "user")
//@DynamicInsert // insert할때 null인 필드 제외
public class User {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.AUTO) // 프로젝트에 연결된 디비(mysql)의 넘버링 전략을 따른다.
    private int id; // 고유 번호

    @Column(nullable = false,length = 30, unique = true) // 해당 열은 널값이 들어가면 안된다.
    private String username; // 유저의 아이디

    @Column(nullable = false, length = 300) // 패스워드 길이는 300으로 제한
    private String password; // 유저의 비밀번호(auto_increment)

    @Column(nullable = false, length = 50)
    private String email; // 유저의 이메일

//    @ColumnDefault("'user'")  // 기본은 user
//    private String role; // Enum을 쓰는 것이 좋다. / admin, user, manager등의 권한 관리
    // db는 RoleType이 없다.
    @Enumerated(EnumType.STRING) // String임을 알린다.
    private RoleType role;

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate; // 시간
}
