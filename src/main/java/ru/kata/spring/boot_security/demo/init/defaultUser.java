package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Gender;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepo;
import ru.kata.spring.boot_security.demo.repository.UserRepo;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class defaultUser {

    private UserServiceImpl userRepo;
    private RoleServiceImpl roleRepo;


    @Autowired
    public defaultUser(RoleServiceImpl roleRepo, UserServiceImpl userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;

    }


    @Transactional
    @PostConstruct
    public void loadTestUsers() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");


        roleRepo.add(userRole);
        roleRepo.add(adminRole);


        User user = new User(10, Gender.FEMALE, "user", Set.of(userRole), "user");
        User admin = new User(99, Gender.MALE, "admin", Set.of(adminRole), "admin");


        userRepo.add(user);
        userRepo.add(admin);
    }
}
