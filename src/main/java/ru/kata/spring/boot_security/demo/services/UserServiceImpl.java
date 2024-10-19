package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepo;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void merge(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void delete(String name) {
        userRepo.delete(userRepo.findByUsername(name));
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void delete(Long id) {
        userRepo.delete(userRepo.getById(id));
    }


    @Override
    public User findByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public User get(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public boolean usernameExists(String username) {
        return  userRepo.findByUsername(username) != null;
    }

}
