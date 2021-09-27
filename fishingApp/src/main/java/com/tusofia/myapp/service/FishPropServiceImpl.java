package com.tusofia.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishProp;
import com.tusofia.myapp.repository.FishProposalRep;

@Service
public class FishPropServiceImpl implements FishProposalService {

	@Autowired
    private FishProposalRep fishPropRep;
	
	
	@Override
	public void save(FishProp fishProp) {
		fishPropRep.save(fishProp);
		
	}

	@Override
	public ArrayList<FishProp> findAll() {
	
		return  fishPropRep.findAll();
	}

	@Override
	public void deleteById(Long id) {
		fishPropRep.deleteById(id);
		
	}

	@Override
	public FishProp findById(Long id) {
		return fishPropRep.findById(id).get();
	}

	@Override
	public Fish proposalToRecord(FishProp fishProp) {
		Fish fish=new Fish();
		fish.setInfo(fishProp.getFishInfo());
		fish.setMaxSize(fishProp.getMaxSize());
		fish.setMaxWeight(fishProp.getMaxWeight());
		fish.setName(fishProp.getName());
		fish.setPath(fishProp.getPath());
		return fish;
	}

}
