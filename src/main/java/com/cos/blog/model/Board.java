package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더 패턴
@Table(name = "board")
@Entity
// ORM -> 자바(혹은 다른 언어)의 객체를 db의 테이블로 매핑하는 기술
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title; // 제목

    @Lob // 대용량 데이터에 사용
    private String content;

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, one = User 즉 여러개의 게시글은 한명의 유저에 의해 작성될 수 있다.
    @JoinColumn(name = "userId")
    private User user; // db는 오브젝트를 저장 불가, 자바는 오브젝트 저장 여기서 충돌 발생 따라서 자바에서 db에 맞춰 저장한다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아님, 즉 fk가 아니다. db에 컬럼을 만들지 않는다.
    // board는 Reply클래스 필드에 있는 Board board다.
    //fk 필요 없다.
    @JsonIgnoreProperties({"board"}) // Reply 내에서 Board를 또 호출하지 않음
    @OrderBy("id desc")
    private List<Reply> replies; // 게시글 하나에 여러개의 reply가 올 수 있기 때문에 List로 지정

    @CreationTimestamp
    private Timestamp createDate;

}
