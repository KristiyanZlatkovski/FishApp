package com.tusofia.myapp.service;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;
import com.tusofia.myapp.dto.JournalGetDto;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;


public interface JournalService {
	
	long count();
	void save(FishJournal fishJournal);
	ArrayList<JournalGetDto> findByUsersAndWatersAndFishes(User user,Water water,Fish fish);
	ArrayList<JournalGetDto> findByUsersAndWaters(User user,Water water);
	ArrayList<JournalGetDto> findByUsersAndFishes(User user,Fish fish);
	ArrayList<JournalGetDto> findByWatersAndFishesAndShared(Water water,Fish fish,boolean shared);
	ArrayList<JournalGetDto> findByWatersAndShared(Water water,boolean shared);
	ArrayList<JournalGetDto> findByFishesAndShared(Fish fish,boolean shared);
	ArrayList<JournalGetDto> findByUsers(User user);
	ArrayList<FishJournal>   findAllByUser(User user);
	void saveAll(ArrayList<FishJournal> list);
	void changeOwner(Long id,Long newId);
	ArrayList<JournalGetDto> findByShared(boolean shared);
	ArrayList<JournalGetDto> journalToDto(ArrayList<FishJournal> fishJournal);
	ArrayList<FishJournal>   getTourneyEntries(Tournament tournament);
	ArrayList<JournalGetDto> findAllUnmoderated();
	void deleteById(Long id);
	FishJournal getJournalById(Long id);
	FishJournal logInitialise(FishJournal logForm, User currentUser, MultipartFile multipartFile) throws IOException;
	ArrayList<JournalGetDto> findLogs(User currentUser, String waterId, String fishId, boolean shared);
}
