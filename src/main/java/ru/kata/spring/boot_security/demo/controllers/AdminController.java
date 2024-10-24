package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String main(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("userDetails", (User)userDetails);
        model.addAttribute("all_users", userService.findAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("new_user", new User());

        return "admin/admin_bootstrap5";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("deleteId")  Long deleteId) {
        userService.delete(deleteId);
        return "redirect:/admin";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute User user, Model model) {
        if (user.getId() == null && userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "Пользователь с такой почтой уже существует.");
            model.addAttribute("roles", roleService.getAllRoles());
            return "redirect:/admin"; // Вернем на страницу редактирования с ошибкой
        }
        userService.add(user);
        return "redirect:/admin";
    }

}
