package com.tusofia.myapp.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.Tournament;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.service.CommentService;
import com.tusofia.myapp.service.FishService;
import com.tusofia.myapp.service.JournalService;
import com.tusofia.myapp.service.TournamentService;
import com.tusofia.myapp.service.WaterService;

@Controller
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private FishService fishService;

    @Autowired
    private WaterService waterService;


    @Autowired
    private CommentService commentService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private JournalService journalService;

    @GetMapping("/fishInfo")
    public String getFishInfo(@RequestParam String fish, Model model) {
        Fish fish1 = fishService.findByName(fish);
        model.addAttribute("waters", waterService.findAllByFish(fish1));
        model.addAttribute("fish", fish1);
        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(fish1.getId(), "fish"));
        model.addAttribute("comment", new Comment());

        return "fishInfo";
    }


    @GetMapping("/browseFishList")
    public String fishList(Model model) {


        model.addAttribute("fishes", fishService.findAll());


        return "browseFishes";
    }


    @GetMapping("/browseWaterList")
    public String waterList(Model model) {


        model.addAttribute("waters", waterService.findAll());


        return "browseWaters";
    }

    @GetMapping("/waterInfo")
    public String getWaterInfo(@RequestParam String water, Model model) {

        Water water1 = waterService.findByName(water);
        model.addAttribute("fishes", fishService.findAllByWaters(water1));
        model.addAttribute("water", water1);
        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(water1.getId(), "water"));
        model.addAttribute("comment", new Comment());


        return "waterInfo";
    }


    @GetMapping("/browseTournaments")
    public String getAllTournaments(Model model) {

        ArrayList<Tournament> tournaments = tournamentService.findAll();
        tournamentService.checkTournamentsExpiration(tournaments);
        model.addAttribute("tournaments", tournaments);

        return "browseTournaments";
    }


    @GetMapping("/test")
    public String defaultLogin(Model model) {


        return "redirect:/home";
    }

    @GetMapping("/tournamentInfo")
    public String getTournamentInfo(@RequestParam int tournament, Model model) {
        Tournament tour = new Tournament();
        try {
            tour = tournamentService.findById((long) tournament);
        } catch (Exception ex) {

            return "error";
        }
        model.addAttribute("tournament", tour);
        model.addAttribute("entries", journalService.getTourneyEntries(tour));
        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(tour.getId(), "tournament"));
        model.addAttribute("comment", new Comment());

        return "tournamentInfo";
    }


}
