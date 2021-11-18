package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;

public interface WaterService {


    Water findByName(String name);


    ArrayList<Water> findAll();

    void saveWater(Water water);

    Water findById(Long id);

    ArrayList<Water> findAllByFish(Fish fish);
}
