package com.tusofia.myapp.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.repository.TournamentRep;
import com.tusofia.myapp.repository.TournamentRewardsRep;

@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentRep tournamentRep;
    @Autowired
    private TournamentRewardsRep rewardRep;

    @Override
    public Tournament findById(Long id) {
        return tournamentRep.findById(id).get();
    }

    @Override
    public void save(Tournament tournament) {
        tournamentRep.save(tournament);

    }

    @Override
    public ArrayList<Tournament> findAll() {

        return (ArrayList<Tournament>) tournamentRep.findAll();
    }

    @Override
    public Tournament makeNewTournament(Tournament tournament, long fish, long water, int days) {
        Fish currentFish = new Fish();
        Water currentWater = new Water();
        if (!(fish == -1)) {
            currentFish.setId(fish);
            tournament.setFishes(currentFish);
        }
        if (!(water == -1)) {
            currentWater.setId(water);
            tournament.setWaters(currentWater);
        }


        tournament.setStatus("Активен");
        tournament.addReward(createReward(tournament.getName(), 1, 100));
        tournament.addReward(createReward(tournament.getName(), 2, 50));
        tournament.addReward(createReward(tournament.getName(), 3, 25));


        Date date = new Date();
        tournament.setStartingDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        tournament.setEndDate(cal.getTime());

        return tournament;

    }


    private TournamentRewards createReward(String name, int place, double value) {
        TournamentRewards curent = new TournamentRewards();
        curent.setName(name + ",place(" + place + ")reward");
        curent.setPlacing(place);
        curent.setMonetaryValue(value);
        return curent;
    }

    @Override
    public void checkTournamentsExpiration(ArrayList<Tournament> tournaments) {
        Date curentDate = new Date();
        for (Tournament tournament : tournaments) {
            if (tournament.getEndDate().before(curentDate) && tournament.getStatus().equals("Активен")) {
                tournament.setStatus("Затворен");
                tournamentRep.save(tournament);
            }
        }

    }

    @Override
    public void giveRewards(ArrayList<FishJournal> entries, ArrayList<TournamentRewards> rewards) {

        for (int i = 0; i < rewards.size() && i < entries.size(); i++) {

            TournamentRewards reward = rewards.get(i);


            User user = new User();
            user.setId(entries.get(i).getId());
            reward.setUser(user);
            rewardRep.save(reward);


        }


    }


}
