package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Gender;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam Long delete) {
        userService.delete(delete);
        return "redirect:/admin";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam Long edit, Model model) {

        model.addAttribute("user", userService.get(edit));
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("genders", Gender.values());

        return "admin/edit";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute User user, Model model) {
        if (user.getId() == null && userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "Пользователь с таким именем уже существует.");
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("genders", Gender.values());
            return "admin/edit"; // Вернем на страницу редактирования с ошибкой
        }
        userService.add(user);
        return "redirect:/admin";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("genders", Gender.values());

        return "admin/edit";
    }
}
