package com.tusofia.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Role;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.repository.RoleRepository;
import com.tusofia.myapp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public void saveUpdate(User user) {

        userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public ArrayList<Role> findAllRoles() {

        return roleRepository.findAll();
    }

    @Override
    public User getById(Long id) {

        return userRepository.getById(id);
    }

    @Override
    public Role findRoleById(Long id) {

        return roleRepository.findById(id).get();
    }

    @Override
    public void transferUserRecords(Long id) {


    }
}