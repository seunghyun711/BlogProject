package com.cos.blog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne // 여러개의 답변은 하나의 게시글에 존재할 수 있다.
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    @Override
    public String toString() {
        return "Reply [id=" + replyId + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
                + createDate + "]"; // 오브젝트를 출력하면 자동으로 toString()이 호출됨
    }

}
