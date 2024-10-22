package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class defaultUser {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    @Autowired
    public defaultUser(RoleServiceImpl roleRepo, UserServiceImpl userRepo) {
        this.roleService = roleRepo;
        this.userService = userRepo;

    }


    @Transactional
    @PostConstruct
    public void loadTestUsers() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");


        roleService.add(userRole);
        roleService.add(adminRole);


        User user = new User("user@gmail.com","user",Set.of(userRole),22,"Saske","Utiha");
        User admin = new User("admin@gmail.com","admin",Set.of(adminRole),22,"Naruto","Uzumaki");


        userService.add(user);
        userService.add(admin);
    }
}
