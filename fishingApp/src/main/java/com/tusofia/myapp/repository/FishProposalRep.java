package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.FishProp;


public interface FishProposalRep extends JpaRepository<FishProp, Long> {


    ArrayList<FishProp> findAll();

}
