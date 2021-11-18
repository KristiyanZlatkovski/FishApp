package com.tusofia.myapp.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;


public interface WaterRepository extends JpaRepository<Water, Long> {
    Water findByName(String name);

    ArrayList<Water> findAll();

    ArrayList<Water> findAllByFishes(Fish fish);

}