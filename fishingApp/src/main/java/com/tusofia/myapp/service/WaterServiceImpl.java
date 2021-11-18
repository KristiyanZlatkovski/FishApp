package com.tusofia.myapp.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.repository.WaterRepository;


@Service
public class WaterServiceImpl implements WaterService {
    @Autowired
    private WaterRepository waterRepository;


    @Override
    public ArrayList<Water> findAll() {
        // TODO Auto-generated method stub
        return waterRepository.findAll();
    }


    @Override
    public Water findByName(String name) {
        // TODO Auto-generated method stub
        return waterRepository.findByName(name);
    }


    @Override
    public Water findById(Long id) {
        return waterRepository.findById(id).get();
    }


    @Override
    public void saveWater(Water water) {

        waterRepository.save(water);
    }


    @Override
    public ArrayList<Water> findAllByFish(Fish fish) {

        return waterRepository.findAllByFishes(fish);
    }
}