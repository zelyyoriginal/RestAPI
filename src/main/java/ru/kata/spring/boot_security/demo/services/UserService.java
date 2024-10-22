package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public void add(User user);
    public List<User> findAll();

    public void delete(String name);
    public void delete(User user);
    public void delete(Long id);

    public User findByUserEmail(String name);
    public User get(Long id);
    public boolean usernameExists(String username);
}
