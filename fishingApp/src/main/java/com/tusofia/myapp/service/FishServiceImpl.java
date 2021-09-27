package com.tusofia.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.repository.FishRepository;


@Service
public class FishServiceImpl implements FishService {
    @Autowired
    private FishRepository fishRepository;
 



	@Override
	 public Fish findByName(String name) {
		// TODO Auto-generated method stub
		return fishRepository.findByName(name);
	}




	@Override
	public ArrayList<Fish> findAllByWaters(Water water) {
		// TODO Auto-generated method stub
		return fishRepository.findAllByWaters(water);
	}




	@Override
	public List<Fish> findAll() {
		// TODO Auto-generated method stub
		return fishRepository.findAll();
	}
	@Override
	public Fish findById(Long id) {
		return fishRepository.findById(id).get();
	}




	@Override
	public ArrayList<Fish> findAllByWatersNot(Water water) {
		// TODO Auto-generated method stub
		return fishRepository.findAllByWatersNot(water);
	}




	@Override
	public void save(Fish fish) {
		fishRepository.save(fish);
		
	}
}
