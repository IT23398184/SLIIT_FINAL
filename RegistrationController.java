package com.victorp.controller;

// Importing necessary packages and dependencies for the controller

import com.victorp.model.User;
import com.victorp.service.SecurityService;
import com.victorp.service.UserService;
import com.victorp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller  // Marks this class as a Spring MVC Controller
public class RegistrationController {

    // Services and validators used in this controller
    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;
// Constructor injection for dependencies
    @Autowired
    public RegistrationController(UserService userService,SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    // Handles GET requests to the login page
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login"; // Returns the login view
    }

     // Handles GET requests for registration and initializes a User object in the model
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
     // Handles GET requests for client registration
    @GetMapping("/registrationClient")
    public String registrationClient(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationClient";
    }
    // Handles GET requests for admin registration
    @GetMapping("/registrationAdmin")
    public String registrationAdmin(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationAdmin";
    }
    // Handles GET requests for trainer registration
    @GetMapping("/registrationTrainers")
    public String registrationTrainers(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationTrainers";
    }
    
// Handles POST requests for general registration
    @PostMapping("/registration")
    public String registrationClient(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userService.saveClient(userForm)) { // Checks if a user with the same username already exists
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "login";// Redirects to login page with an error message
        }

        userService.create(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";// Redirects to the homepage
    }
    // Handles POST requests for client registration
    @PostMapping("/registrationClient")
    public String addClient(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationClient";// Returns to the client registration page if validation errors exist
        }



        userService.create(userForm);
        userService.saveClient(userForm);

        return "administration";
    }
    @PostMapping("/registrationTrainers")
    public String addTrainer(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationTrainers";
        }

        if (!userService.saveTrainer(userForm)) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "registrationTrainers";
        }

        userService.create(userForm);
        return "administration";
    }

    @PostMapping("/registrationAdmin")
    public String addAdmin(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationAdmin";
        }
        if (!userService.saveAdmin(userForm)) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "registrationAdmin";
        }

        userService.create(userForm);
        return "administration";
    }
    
    


}
