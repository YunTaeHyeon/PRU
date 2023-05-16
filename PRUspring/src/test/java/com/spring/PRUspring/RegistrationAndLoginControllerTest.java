package com.spring.PRUspring;

import com.spring.PRUspring.domain.User;
import com.spring.PRUspring.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationAndLoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // 테스트를 위한 초기화 작업
        userRepository.deleteAll();
    }

    @Test
    public void testRegistration() {
        // 회원가입 테스트
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "password";

        MultiValueMap<String, String> registrationData = new LinkedMultiValueMap<>();
        registrationData.add("username", username);
        registrationData.add("email", email);
        registrationData.add("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity("/register", registrationData, String.class);

        // 회원가입이 성공적으로 처리되었는지 확인
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        // 회원가입 후 생성된 사용자 정보가 데이터베이스에 저장되었는지 확인
        User savedUser = userRepository.findByEmail(email);
        assertNotNull(savedUser);
        assertEquals(username, savedUser.getUsername());
    }

    @Test
    public void testLogin() {
        // 로그인 테스트
        String email = "testuser@example.com";
        String password = "password";

        MultiValueMap<String, String> loginData = new LinkedMultiValueMap<>();
        loginData.add("email", email);
        loginData.add("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity("/login", loginData, String.class);

        // 로그인이 성공적으로 처리되었는지 확인
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // 로그인 후 응답에 특정 데이터 또는 쿠키 등이 포함되어 있는지 확인
        // 필요에 따라 추가적인 검증을 수행할 수 있습니다.
    }

}