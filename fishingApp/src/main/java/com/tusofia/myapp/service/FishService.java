package com.tusofia.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;

public interface FishService {
	  Fish findByName(String name);
	  
		ArrayList<Fish> findAllByWaters(Water water);
		ArrayList<Fish> findAllByWatersNot(Water water);
		List<Fish> findAll();
		Fish findById(Long id);

		void save(Fish fish);
}
