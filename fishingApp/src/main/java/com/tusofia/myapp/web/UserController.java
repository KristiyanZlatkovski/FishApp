package com.tusofia.myapp.web;


import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tusofia.myapp.utility.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.service.FishService;
import com.tusofia.myapp.service.SecurityService;
import com.tusofia.myapp.service.UserService;
import com.tusofia.myapp.service.WaterService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private WaterService waterService;

    @Autowired
    private FishService fishService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());


        return "redirect:/registration/succes";
    }


    @GetMapping("/registration/succes")
    public String registrationSucces(Model model) {


        model.addAttribute("successMessage", "Поздравления вие се регистрирахте успешно");
        model.addAttribute("redirectLink", "/home");

        return "succes";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    @GetMapping({"/home"})
    public String home(Model model) {
        model.addAttribute("waters", waterService.findAll());
        model.addAttribute("fishList", new ArrayList<Fish>());
        return "home";
    }


    @GetMapping({"/"})
    public String homeShort() {

        return "redirect:/home";
    }


    @GetMapping(value = "/getFishesByWater")

    public String findAllFishesByWater(@RequestParam String name, Model model) {

        Water waters = waterService.findByName(name);
        model.addAttribute("fishList", fishService.findAllByWaters(waters));


        return "home :: #sidenavList";

    }


    @ExceptionHandler(Exception.class)
    public @ResponseBody
    String handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return e.getMessage();
    }


}	
    
    

