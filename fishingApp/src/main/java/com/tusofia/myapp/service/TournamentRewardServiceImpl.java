package com.tusofia.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;
import com.tusofia.myapp.repository.TournamentRewardsRep;
@Service
public class TournamentRewardServiceImpl implements TournamentRewardService{

	@Autowired
    private TournamentRewardsRep rewardRep;
	
	
	
	@Override
	public ArrayList<TournamentRewards> findAllByTournament(Tournament tournament) {
		
		return rewardRep.findAllByTournament(tournament,Sort.by(Sort.Direction.ASC, "placing"));
	}
	

}
