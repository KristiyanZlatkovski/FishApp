package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findByName(String name);
    ArrayList<Role> findAll();
}
