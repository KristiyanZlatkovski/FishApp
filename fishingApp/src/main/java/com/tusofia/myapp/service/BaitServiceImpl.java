package com.tusofia.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Bait;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.repository.BaitRepository;


@Service
public class BaitServiceImpl implements BaitService {
    @Autowired
    private BaitRepository baitRepository;
 



	@Override
	public ArrayList<Bait> findAll() {
		// TODO Auto-generated method stub
		return baitRepository.findAll();
	}




	@Override
	public Bait findByName(String name) {
		// TODO Auto-generated method stub
		return baitRepository.findByName(name);
	}




	@Override
	public Bait findById(long baitId) {
		
		return baitRepository.findById(baitId).get();
	}
	
	
	@Override
	public ArrayList<Bait> findAllByFishes(Fish fish) {
	
		return baitRepository.findAllByFishes(fish);
	}
	
}
