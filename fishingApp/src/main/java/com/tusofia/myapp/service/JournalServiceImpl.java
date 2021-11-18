package com.tusofia.myapp.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tusofia.myapp.dto.JournalGetDto;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.repository.JournalRepository;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private PictureService pictureService;


    @Override
    public long count() {
        // TODO Auto-generated method stub
        return journalRepository.count();
    }


    @Override
    public void save(FishJournal fishJournal) {
        journalRepository.save(fishJournal);

    }


    @Override
    public ArrayList<JournalGetDto> findByUsersAndWatersAndFishes(User user, Water water, Fish fish) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByUsersAndWatersAndFishes(user, water, fish);


        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByUsersAndWaters(User user, Water water) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByUsersAndWaters(user, water);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByUsersAndFishes(User user, Fish fish) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByUsersAndFishes(user, fish);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByWatersAndShared(Water water, boolean shared) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByWatersAndShared(water, shared);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByFishesAndShared(Fish fish, boolean shared) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByFishesAndShared(fish, shared);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByShared(boolean shared) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByShared(shared);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByWatersAndFishesAndShared(Water water, Fish fish, boolean shared) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByWatersAndFishesAndShared(water, fish, shared);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> findByUsers(User user) {
        // TODO Auto-generated method stub
        ArrayList<FishJournal> journal = journalRepository.findByUsers(user);
        return journalToDto(journal);
    }


    @Override
    public ArrayList<JournalGetDto> journalToDto(ArrayList<FishJournal> fishJournal) {
        ArrayList<JournalGetDto> dto = new ArrayList<JournalGetDto>(fishJournal.size());
        for (int i = 0; i < fishJournal.size(); i++) {
            JournalGetDto dtoElement = new JournalGetDto();
            dtoElement.setId(fishJournal.get(i).getId());
            dtoElement.setInfo(fishJournal.get(i).getInfo());
            dtoElement.setBaitName(fishJournal.get(i).getBaits().getName());
            dtoElement.setWaterName(fishJournal.get(i).getWaters().getName());
            dtoElement.setUserName(fishJournal.get(i).getUsers().getNickname());
            dtoElement.setFishName(fishJournal.get(i).getFishes().getName());
            dtoElement.setDate(fishJournal.get(i).getDate());
            dtoElement.setPath(fishJournal.get(i).getPath());
            dtoElement.setSize(fishJournal.get(i).getSize());
            dtoElement.setWeight(fishJournal.get(i).getWeight());


            dto.add(i, dtoElement);
        }
        return dto;
    }


    @Override
    public ArrayList<FishJournal> getTourneyEntries(Tournament tournament) {


        Date start = tournament.getStartingDate();
        Date end = tournament.getEndDate();
        Optional<Water> water = tournament.getWaters();
        Optional<Fish> fish = tournament.getFishes();
        ArrayList<FishJournal> journal = new ArrayList<FishJournal>();

        if (!water.isPresent() && !fish.isPresent()) {
            journal = journalRepository.findByIsEligibleForTourneyAndDateBetween(true, start, end, Sort.by(Sort.Direction.DESC, "weight"));
        } else if (!water.isPresent()) {
            journal = journalRepository.findByIsEligibleForTourneyAndFishesAndDateBetween(true, fish, start, end, Sort.by(Sort.Direction.DESC, "weight"));
        } else if (!fish.isPresent()) {
            journal = journalRepository.findByIsEligibleForTourneyAndWatersAndDateBetween(true, water, start, end, Sort.by(Sort.Direction.DESC, "weight"));
        } else {
            journal = journalRepository.findByIsEligibleForTourneyAndWatersAndFishesAndDateBetween(true, water, fish, start, end, Sort.by(Sort.Direction.DESC, "weight"));
        }

        return journal;
    }


    @Override
    public ArrayList<JournalGetDto> findAllUnmoderated() {
        ArrayList<FishJournal> journal = new ArrayList<FishJournal>();

        journal = journalRepository.findAllByIsModerated(false);
        return journalToDto(journal);
    }


    @Override
    public void deleteById(Long id) {

        journalRepository.deleteById(id);
    }


    @Override
    public FishJournal getJournalById(Long id) {

        return journalRepository.getById(id);
    }


    @Override
    public FishJournal logInitialise(FishJournal logForm, User currentUser, MultipartFile multipartFile) throws IOException {
        logForm.setPath(pictureService.getJournalPicUploadPath(multipartFile));
        if (multipartFile.isEmpty() || logForm.getSize() == 0 || logForm.getWeight() == 0 || !logForm.isShared()) {
            logForm.setEligibleForTourney(false);
        } else {
            logForm.setEligibleForTourney(true);
        }

        logForm.setDate(new Date());
        logForm.setUsers(currentUser);
        return logForm;
    }


    @Override
    public ArrayList<JournalGetDto> findLogs(User currentUser, String water, String fish, boolean shared) {
        if (water.equals("")) {
            if (fish.equals("")) {
                if (shared == true) {
                    return findByUsers(currentUser);
                } else {
                    return findByShared(true);
                }
            } else {
                Fish currentFish = new Fish();
                currentFish.setId(Long.valueOf(fish));
                if (shared == true) {
                    return findByUsersAndFishes(currentUser, currentFish);
                } else {
                    return findByFishesAndShared(currentFish, true);
                }


            }
        } else {
            Water currentWater = new Water();
            currentWater.setId(Long.valueOf(water));

            if (fish.equals("")) {
                if (shared == true) {
                    return findByUsersAndWaters(currentUser, currentWater);
                } else {
                    return findByWatersAndShared(currentWater, true);
                }
            } else {
                Fish currentFish = new Fish();
                currentFish.setId(Long.valueOf(fish));
                if (shared == true) {
                    return findByUsersAndWatersAndFishes(currentUser, currentWater, currentFish);
                } else {
                    return findByWatersAndFishesAndShared(currentWater, currentFish, true);
                }
            }
        }
    }


    @Override
    public ArrayList<FishJournal> findAllByUser(User user) {

        return journalRepository.findByUsers(user);
    }


    @Override
    public void changeOwner(Long id, Long newId) {
        User user = new User();
        User newuser = new User();
        user.setId(id);
        newuser.setId(newId);

        ArrayList<FishJournal> currentList = findAllByUser(user);
        for (int i = 0; i < currentList.size(); i++) {

            currentList.get(i).setUsers(newuser);

            journalRepository.saveAll(currentList);
        }


    }


    @Override
    public void saveAll(ArrayList<FishJournal> list) {


        journalRepository.saveAll(list);

    }


}
