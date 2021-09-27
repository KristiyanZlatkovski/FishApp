package com.tusofia.myapp.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;


public interface JournalRepository extends JpaRepository<FishJournal, Long>{
	
	
	ArrayList<FishJournal> findByUsersAndWatersAndFishes(User user,Water water,Fish fish);
	ArrayList<FishJournal> findByUsersAndWaters(User user,Water water);
	ArrayList<FishJournal> findByUsersAndFishes(User user,Fish fish);
	ArrayList<FishJournal> findByWatersAndFishesAndShared(Water water,Fish fish,boolean shared);
	ArrayList<FishJournal> findByWatersAndShared(Water water,boolean shared);
	ArrayList<FishJournal> findByFishesAndShared(Fish fish,boolean shared);
	
	ArrayList<FishJournal> findByUsers(User user);
	ArrayList<FishJournal> findByShared(boolean shared);
	ArrayList<FishJournal> findByDateBetween(Date start, Date end, Sort sort);
	ArrayList<FishJournal> findByIsEligibleForTourneyAndDateBetween(boolean eligible,Date start, Date end, Sort sort);
	ArrayList<FishJournal> findByIsEligibleForTourneyAndWatersAndFishesAndDateBetween(boolean eligible,Optional<Water> water,Optional<Fish> fish,Date start, Date end, Sort sort);
	ArrayList<FishJournal> findByIsEligibleForTourneyAndFishesAndDateBetween(boolean eligible,Optional<Fish> fish,Date start, Date end, Sort sort);
	ArrayList<FishJournal> findByIsEligibleForTourneyAndWatersAndDateBetween(boolean eligible,Optional<Water> water,Date start, Date end, Sort sort);
	ArrayList<FishJournal> findAllByIsModerated(boolean moderated);
	
}
