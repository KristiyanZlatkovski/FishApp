package com.tusofia.myapp.web;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tusofia.myapp.model.Bait;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.Role;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.TournamentRewards;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.service.BaitService;
import com.tusofia.myapp.service.CommentService;
import com.tusofia.myapp.service.FTWPService;
import com.tusofia.myapp.service.FishProposalService;
import com.tusofia.myapp.service.FishService;
import com.tusofia.myapp.service.JournalService;
import com.tusofia.myapp.service.TournamentRewardService;
import com.tusofia.myapp.service.TournamentService;
import com.tusofia.myapp.service.UserService;
import com.tusofia.myapp.service.WaterProposalService;
import com.tusofia.myapp.service.WaterService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private BaitService baitService;

    @Autowired
    private FTWPService fTWPService;

    @Autowired
    private WaterService waterService;

    @Autowired
    private FishService fishService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private TournamentRewardService rewardService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private WaterProposalService waterPropService;

    @Autowired
    private FishProposalService fishPropService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/listUsers")
    public String showAll(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", userService.findAllRoles());
        return "allUsers";
    }


    @DeleteMapping("/listUsers/removeUser")
    public String removeUser(@RequestParam Long id, Model model) {
        journalService.changeOwner(id, (long) 15);
        commentService.changeOwner(id, (long) 15);

        userService.deleteById(id);
        model.addAttribute("users", userService.findAll());
        return "allUsers :: #userTable";
    }


    @PutMapping("/listUsers/changeUserRole")
    public String changeUserRole(@RequestParam long userId, @RequestParam long roleId) {


        User currentUser = userService.getById(userId);

        Role currentRole = userService.findRoleById(roleId);

        currentUser.setRoles(currentRole);

        userService.saveUpdate(currentUser);


        return "allUsers";
    }


    @GetMapping("/tournament/makeTournament")
    public String makeTournament(Model model) {

        model.addAttribute("tournament", new Tournament());
        model.addAttribute("fishes", fishService.findAll());
        model.addAttribute("waters", waterService.findAll());

        return "makeTournament";
    }


    @PostMapping("/tournament/makeTournament")
    public String makeTournament(@ModelAttribute("tournament") Tournament tournament, @RequestParam("water") long water,
                                 @RequestParam("fish") long fish, @RequestParam("days") int days) {


        tournamentService.save(tournamentService.makeNewTournament(tournament, fish, water, days));


        return "redirect:/";
    }


    @PutMapping("/tournament/distributeRewards")
    public String distributeRewards(@RequestParam Long id, Model model) {

        Tournament tour = tournamentService.findById(id);
        ArrayList<FishJournal> entries = journalService.getTourneyEntries(tour);
        ArrayList<TournamentRewards> rewards = rewardService.findAllByTournament(tour);
        tournamentService.giveRewards(entries, rewards);
        tour.setStatus("Завършил");
        tournamentService.save(tour);

        model.addAttribute("tournaments", tournamentService.findAll());
        return "/browseTournaments :: #tourTable";

    }


    @GetMapping("/manageFishes/getAllBaits")
    public String getAllBaits(@RequestParam long fishId, Model model) {

        Fish fish = fishService.findById(fishId);
        model.addAttribute("baitsByFish", baitService.findAllByFishes(fish));

        return "addBaitToFish :: #baitByFishTable";

    }


    @GetMapping("/manageWaters/getAllFishByWater")
    public String findAllFishByWater(@RequestParam long waterId, Model model) {

        Water waters = waterService.findById(waterId);

        model.addAttribute("fishesByWater", fishService.findAllByWaters(waters));

        return "addFishToWater :: #fishByWaterTable";

    }


    @PostMapping("/manageWaters/saveFishToWater")
    public String saveFishToWater(@RequestParam long waterId, @RequestParam long fishId, Model model) {
        Water waters = waterService.findById(waterId);

        waters.addFish(fishService.findById(fishId));
        waterService.saveWater(waters);
        model.addAttribute("fishesByWater", fishService.findAllByWaters(waters));

        return "addFishToWater :: #fishByWaterTable";
    }

    @PostMapping("/manageFishes/saveBaitToFish")
    public String saveBaitToFish(@RequestParam long baitId, @RequestParam long fishId, Model model) {
        Fish fish = fishService.findById(fishId);

        fish.addBait(baitService.findById(baitId));
        fishService.save(fish);
        model.addAttribute("baitsByFish", baitService.findAllByFishes(fish));

        return "addBaitToFish :: #baitByFishTable";
    }


    @DeleteMapping("/manageWaters/removeFishFromWater")
    public String removeFishFromWater(@RequestParam long waterId, @RequestParam long fishId, Model model) {
        Water waters = waterService.findById(waterId);

        waters.removeFish(fishService.findById(fishId));
        waterService.saveWater(waters);
        model.addAttribute("fishesByWater", fishService.findAllByWaters(waters));


        return "addFishToWater :: #fishByWaterTable";
    }


    @DeleteMapping("/manageFishes/removeBaitFromFish")
    public String removeBaitFromFish(@RequestParam long baitId, @RequestParam long fishId, Model model) {
        Fish fish = fishService.findById(fishId);

        fish.removeBait(baitService.findById(baitId));
        fishService.save(fish);
        model.addAttribute("baitsByFish", baitService.findAllByFishes(fish));


        return "addBaitToFish :: #baitByFishTable";
    }


    @GetMapping("/manageWaters/addFishToWater")
    public String addFishToWater(Model model) {
        model.addAttribute("waters", waterService.findAll());
        model.addAttribute("fishes", fishService.findAll());
        model.addAttribute("fishesByWater", new ArrayList<Fish>());
        return "addFishToWater";
    }

    @GetMapping("/manageFishes/addBaitToFish")
    public String addBaitToFish(Model model) {
        model.addAttribute("baits", baitService.findAll());
        model.addAttribute("fishes", fishService.findAll());
        model.addAttribute("baitsByFish", new ArrayList<Bait>());
        return "addBaitToFish";
    }


    @GetMapping("/proposals/browseProposals")
    public String browseProps(Model model) {
        model.addAttribute("waterProps", waterPropService.findAll());
        model.addAttribute("fishProps", fishPropService.findAll());
        model.addAttribute("fishToWaterProps", fTWPService.findAll());

        return "browseProposals";
    }


}
