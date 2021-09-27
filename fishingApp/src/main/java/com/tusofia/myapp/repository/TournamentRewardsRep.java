package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;




public interface TournamentRewardsRep extends JpaRepository<TournamentRewards, Long>{
	
	ArrayList<TournamentRewards> findAllByTournament(Tournament tournament,Sort sort);
	
}
