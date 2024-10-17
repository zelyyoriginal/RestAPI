package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public void add(Role role);
    public void remove(Role role);
    public Role getRole(String roleName);
    public Set<Role> getAllRoles();

}
