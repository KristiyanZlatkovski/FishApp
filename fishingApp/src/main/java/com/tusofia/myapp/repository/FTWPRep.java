package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tusofia.myapp.model.FishToWaterProp;


public interface FTWPRep extends JpaRepository<FishToWaterProp, Long> {

    ArrayList<FishToWaterProp> findAll();
}
