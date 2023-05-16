package com.spring.PRUspring.controller;

import com.spring.PRUspring.domain.User;
import com.spring.PRUspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Value("${upload.path}") // application.properties에 설정한 파일 업로드 경로
    private String uploadPath;

    /*
    @GetMapping("/register") //builder 패턴 사용하지 않았을 때
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    */

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = User.builder().build(); // 빌더 패턴 사용
        model.addAttribute("user", user);
        return "register";
    }
/*
    @PostMapping("/register") //유저네임과 이메일만
    public String processRegistration(@RequestParam("username") String username, @RequestParam("email") String email) {
        // 요청 파라미터를 직접 수신하여 처리합니다.
        // 필요한 유효성 검사 등을 수행합니다.

        // User 엔티티를 생성하고 필드 값을 설정합니다.
        User user = User.builder()
                .username(username)
                .email(email)
                .build();

        // 회원가입 로직 처리
        userRepository.save(user);

        return "redirect:/login";
    }
*/
    /*
    @PostMapping("/register") //프로필 이미지가 null이면 안됨
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("email") String email,
                                      @RequestPart("profileImage") MultipartFile profileImage) {
        // username과 email 값을 받아 회원가입 로직 처리
        // 프로필 이미지도 받아서 처리하는 로직 추가

        if (!profileImage.isEmpty()) {
            try {
                // 파일 저장 로직 구현
                String fileName = UUID.randomUUID().toString() + "-" + profileImage.getOriginalFilename();
                String filePath = uploadPath + "/" + fileName;
                profileImage.transferTo(new File(filePath));

                User newUser = User.builder()
                        .username(username)
                        .email(email)
                        .profileImagePath(filePath)
                        .build();

                userRepository.save(newUser);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 저장 실패 시 예외 처리
            }
        }

        return "redirect:/login";
    }
*/
    @PostMapping("/register") //프로필 이미지가 null이어도 됨
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
        // username과 email 값을 받아 회원가입 로직 처리
        // 프로필 이미지도 받아서 처리하는 로직 추가

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                // 파일 저장 로직 구현
                String fileName = UUID.randomUUID().toString() + "-" + profileImage.getOriginalFilename();
                String filePath = uploadPath + "/" + fileName;
                profileImage.transferTo(new File(filePath));

                User newUser = User.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .profileImagePath(filePath)
                        .build();

                userRepository.save(newUser);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 저장 실패 시 예외 처리
            }
        } else {
            // 프로필 이미지가 null이거나 비어있는 경우의 처리 로직 추가
            User newUser = User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .build();

            userRepository.save(newUser);
        }

        return "redirect:/login";
    }

}

