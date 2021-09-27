package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.WaterProp;

public interface WaterProposalRep extends JpaRepository<WaterProp, Long>{
	
	
	 ArrayList<WaterProp> findAll();
	
}
