package com.tusofia.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Tournament;


public interface TournamentRep extends JpaRepository<Tournament, Long> {


}
