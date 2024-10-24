package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public String load(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = (User) userDetails;
        model.addAttribute("userDetails", user);
        return "user_bootstrap5";
    }

}
