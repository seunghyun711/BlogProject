package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더 패턴
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false, length = 100)
    private String title; // 제목

    @Lob // 대용량 데이터에 사용
    private String content;

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = Board, one = User 즉 여러개의 게시글은 한명의 유저에 의해 작성될 수 있다.
    @JoinColumn(name = "userId")
    private User user; // db는 오브젝트를 저장 불가, 자바는 오브젝트 저장 여기서 충돌 발생 따라서 자바에서 db에 맞춰 저장한다.

    @CreationTimestamp
    private Timestamp createDate;

}
