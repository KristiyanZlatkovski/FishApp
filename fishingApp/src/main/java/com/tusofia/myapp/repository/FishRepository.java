package com.tusofia.myapp.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;


public interface FishRepository extends JpaRepository<Fish, Long> {
    Fish findByName(String name);
 
	ArrayList<Fish> findAllByWaters(Water water);
	ArrayList<Fish> findAllByWatersNot(Water water);
	
}