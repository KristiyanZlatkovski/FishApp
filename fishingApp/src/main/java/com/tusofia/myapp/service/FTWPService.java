package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.FishToWaterProp;



public interface FTWPService {
	
	void save(FishToWaterProp fishToWaterProp);
	 ArrayList<FishToWaterProp> findAll();
	void deleteById(Long id);
	FishToWaterProp findById(Long id);
}
