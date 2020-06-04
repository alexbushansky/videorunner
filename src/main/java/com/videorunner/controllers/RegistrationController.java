package com.videorunner.controllers;


import com.videorunner.models.User;
import com.videorunner.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    public final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {

        if (user.getPassword().length()>25)
        {
            bindingResult.rejectValue("password", "password", "Password cannot be more than 25 characters");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword", "Passwords are different");
        }

        if (!bindingResult.hasErrors()) {
            if (userService.saveUser(user)) {
                return "redirect:/login";
            } else {
                bindingResult.rejectValue("email", "email", "An account already exists for this email.");
                return "/registration";
            }
        } else {
            return "/registration";
        }


    }


    @GetMapping("/registration")
    public String registerPage(@ModelAttribute("user") User user) {
        return "registration";
    }


}
