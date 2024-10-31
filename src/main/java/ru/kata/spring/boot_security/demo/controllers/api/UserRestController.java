package ru.kata.spring.boot_security.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("admin/api/users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {

        if (user.getId() == null && userService.usernameExists(user.getUsername())) {
          return  ResponseEntity.status(HttpStatus.CONFLICT).build();

        }

      userService.add(user);

      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("admin/api/users")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        Long id = userService.findByUserEmail(user.getUsername()).getId();
        if(id.equals(user.getId())) {//в БД есть такой Email и ID совпадают
            userService.add(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


    @GetMapping("admin/api/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @GetMapping("admin/api/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.get(id);
    }
    @GetMapping("admin/api/roles")
    public Set<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("admin/api/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        try {
            userService.get(id);
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @GetMapping("api/auth")
    public User getUserDetails() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return (User) principal;
    }


}
