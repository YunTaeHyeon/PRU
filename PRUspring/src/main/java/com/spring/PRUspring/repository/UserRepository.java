package com.spring.PRUspring.repository;

import com.spring.PRUspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
@Repository
public class UserRepository implements JpaRepository<User,Long> {
}
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 추가적인 메서드 정의
}