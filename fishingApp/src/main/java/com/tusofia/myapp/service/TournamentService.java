package com.tusofia.myapp.service;


import java.util.ArrayList;

import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;


public interface TournamentService {
    Tournament findById(Long id);

    void save(Tournament tournament);

    ArrayList<Tournament> findAll();

    Tournament makeNewTournament(Tournament tournament, long fish, long water, int days);

    void checkTournamentsExpiration(ArrayList<Tournament> tournaments);

    void giveRewards(ArrayList<FishJournal> entries, ArrayList<TournamentRewards> rewards);

}

