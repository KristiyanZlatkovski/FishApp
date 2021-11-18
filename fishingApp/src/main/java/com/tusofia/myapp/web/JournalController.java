package com.tusofia.myapp.web;

import java.io.IOException;

import java.util.ArrayList;


import com.tusofia.myapp.utility.JournalValidator;
import com.tusofia.myapp.utility.PictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tusofia.myapp.dto.JournalGetDto;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.service.BaitService;
import com.tusofia.myapp.service.FishService;
import com.tusofia.myapp.service.JournalService;
import com.tusofia.myapp.service.SecurityService;
import com.tusofia.myapp.service.UserService;
import com.tusofia.myapp.service.WaterService;

@ControllerAdvice
@Controller
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private WaterService waterService;


    @Autowired
    private BaitService baitService;


    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @Autowired
    private FishService fishService;

    @Autowired
    private JournalValidator journalValidator;

    @Autowired
    private PictureValidator pictureValidator;

    @GetMapping("/makeLog")
    public String makeLog(Model model) {
        if (!securityService.isAuthenticated()) {
            return "redirect:/login";
        }

        model.addAttribute("waters", waterService.findAll());
        model.addAttribute("logForm", new FishJournal());
        model.addAttribute("baits", baitService.findAll());
        return "makeLog";
    }


    @PostMapping("/makeLog")
    public String makeLog(@ModelAttribute("logForm") FishJournal logForm,
                          @RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult, Model model, @AuthenticationPrincipal Object principal) throws IOException {
        {

            journalValidator.validate(logForm, bindingResult);
            if (!multipartFile.isEmpty()) {
                pictureValidator.validate(multipartFile, bindingResult);
            }

            if (bindingResult.hasErrors()) {
                model.addAttribute("waters", waterService.findAll());
                model.addAttribute("baits", baitService.findAll());
                return "makeLog";
            }
            String username = ((UserDetails) principal).getUsername();
            User currentUser = userService.findByUsername(username);
            logForm = journalService.logInitialise(logForm, currentUser, multipartFile);
            journalService.save(logForm);


            return "redirect:/";
        }


    }

    @GetMapping("/browseLogs")
    public String browse(Model model) {
        model.addAttribute("waters", waterService.findAll());
        model.addAttribute("fishesByWater", fishService.findAll());
        model.addAttribute("logSearchForm", new FishJournal());
        model.addAttribute("foundLogs", new ArrayList<JournalGetDto>());
        model.addAttribute("userLogsOnly", false);
        return "browseLog";
    }


    @GetMapping("/getFishByWater2")

    public String getFishesByWater2(@RequestParam String water, Model model) {
        Water currentWater = new Water();
        currentWater.setId(Long.valueOf(water));
        model.addAttribute("fishesByWater", fishService.findAllByWaters(currentWater));
        model.addAttribute("logForm", new FishJournal());

        return "makeLog :: #fish";
    }


    @GetMapping("/search")
    public String browseLog(@RequestParam String waterId, @RequestParam String fishId, @RequestParam boolean shared,
                            Model model, @AuthenticationPrincipal Object principal) {

        String username = ((UserDetails) principal).getUsername();
        User currentUser = userService.findByUsername(username);

        ArrayList<JournalGetDto> foundLogs = journalService.findLogs(currentUser, waterId, fishId, shared);

        model.addAttribute("foundLogs", foundLogs);
        if (shared) {
            model.addAttribute("userLogsOnly", true);
        }


        return "browseLog :: #logTable";


    }


    @DeleteMapping("/removeUserJournal")
    public String removeUserLog(@RequestParam Long id) {


        try {
            journalService.deleteById(id);
        } catch (Exception ex) {

        }


        return "browseLog";
    }


}
