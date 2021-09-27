package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.model.WaterProp;

public interface WaterProposalService {
	
	void save(WaterProp waterProp);
	
	ArrayList<WaterProp> findAll();
	void deleteById(Long id);
	WaterProp findById(Long id);

	Water proposalToRecord(WaterProp prop);
}
