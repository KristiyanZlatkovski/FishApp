package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;

public interface TournamentRewardService {
	
	
	ArrayList<TournamentRewards> findAllByTournament(Tournament tournament);

}
