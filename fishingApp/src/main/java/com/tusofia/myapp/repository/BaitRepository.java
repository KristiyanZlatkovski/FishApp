package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Bait;
import com.tusofia.myapp.model.Fish;


public interface BaitRepository extends JpaRepository<Bait, Long> {
    Bait findByName(String name);

    ArrayList<Bait> findAllByFishes(Fish fish);

    ArrayList<Bait> findAll();
}
