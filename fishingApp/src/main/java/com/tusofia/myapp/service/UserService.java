package com.tusofia.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.tusofia.myapp.model.Role;
import com.tusofia.myapp.model.User;

public interface UserService {
    void save(User user);

    void deleteById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User getById(Long id);

    ArrayList<Role> findAllRoles();

    List<User> findAll();

    Role findRoleById(Long id);

    public void saveUpdate(User user);


}