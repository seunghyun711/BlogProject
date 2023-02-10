package com.cos.blog.test;

import lombok.*;

@Data // getter,setter동시 사용 가능
//@AllArgsConstructor // 생성자 만들어진다.
//@RequiredArgsConstructor // final이 붙은 변수에 대한 생성자를 생성한다.
@NoArgsConstructor // 기본 생성자
public class Member {
    /*
    아래 변수들을 private으로 선언하여 해당 변수들에 직접 접근하지 않게하고, 이를 이용하는 메서드를 public으로 만들어 메서드를 통해서만
    접근할 수 있게 만든다.
     */
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder // Member m = Member.builder().username("hong").password("1234").email("sadwlih@aaa").build();의 형식으로 객체 생성할 수 있다.
    // builder패턴을 만들어준다. -> 값을 넣는 순서를 맞추지 않아도 된다. 순서화 필요 x 생성자를 쓸 때는 순서를 맞춰줘야 했다.
    public Member(int id, String username, String password, String email) { // @AllArgsConstructor와 같은 동작을 한다.
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
