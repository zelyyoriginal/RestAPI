package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepo;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void add(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void remove(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public Role getRole(String roleName) {

        return roleRepo.findByName(roleName);
    }

    @Override
    public Set<Role> getAllRoles() {
        return  roleRepo.findAll().stream().collect(Collectors.toSet());
    }
}
