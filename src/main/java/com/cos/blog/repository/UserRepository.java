package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 자동으로 빈으로 등록된다.
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> { // 해당 jpaRepository는 User테이블이 관리하는 레포징토리고 이 User테이블의 pk는 Integer다.


}
