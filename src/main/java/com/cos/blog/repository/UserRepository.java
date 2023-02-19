package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// 자동으로 빈으로 등록된다.
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> { // 해당 jpaRepository는 User테이블이 관리하는 레포징토리고 이 User테이블의 pk는 Integer다.


}

// JPA 네이밍 전략
// select * from user where userName = ? and Password = ? 쿼리 동작
//User findByUserNameAndPassword(String userName, String password);

// 같은 방식
//    @Query(value = "select * from user where userName =?1AND password =?2", nativeQuery = true)
//    User login(String userName, String password);
