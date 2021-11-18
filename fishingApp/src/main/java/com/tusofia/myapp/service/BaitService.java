package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Bait;
import com.tusofia.myapp.model.Fish;

public interface BaitService {


    Bait findByName(String name);


    ArrayList<Bait> findAll();

    ArrayList<Bait> findAllByFishes(Fish fish);

    Bait findById(long baitId);
}
