package com.spring.PRUspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String profileImagePath; // 프로필 사진의 파일 경로

    @Builder
    public User(String username, String email, String password, String profileImagePath) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImagePath = profileImagePath;
    }

}
