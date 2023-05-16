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
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        // 이메일과 비밀번호를 검증하여 로그인 처리 로직을 구현합니다.
        // 필요한 유효성 검사 등을 수행합니다.

        // 이메일과 비밀번호가 일치하는 사용자를 데이터베이스에서 조회합니다.
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공 시 세션 등의 처리를 수행합니다.
            // 예를 들어, 세션에 사용자 정보를 저장하고 홈페이지로 리다이렉트할 수 있습니다.
            // 여기서는 간단히 "loginSuccess"라는 속성을 모델에 추가하고 홈페이지로 이동하도록 설정합니다.
            model.addAttribute("loginSuccess", true);
            return "redirect:/home";
        } else {
            // 로그인 실패 시 에러 메시지를 모델에 추가하고 로그인 페이지로 다시 이동합니다.
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }
    }

    // 홈페이지로 이동하는 핸들러
    @GetMapping("/home")
    public String homePage() {
        // 로그인 성공 시 이동하는 홈페이지로의 경로입니다.
        // 홈페이지로 이동하는 로직을 추가합니다.
        return "home";
    }
}
