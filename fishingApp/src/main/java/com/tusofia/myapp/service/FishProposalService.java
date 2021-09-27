package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishProp;



public interface FishProposalService {

	void save(FishProp fishProp);
	
	ArrayList<FishProp> findAll();

	void deleteById(Long id);

	FishProp findById(Long id);

	Fish proposalToRecord(FishProp fishProp);

}
