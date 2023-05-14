package com.spring.PRUspring.controller;

import com.spring.PRUspring.domain.User;
import com.spring.PRUspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    /*
    @GetMapping("/register")
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

    @PostMapping("/register")
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
}
