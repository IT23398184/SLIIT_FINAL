package com.victorp.controller;

import com.victorp.model.User;
import com.victorp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Controller class for managing user-related views
@RequestMapping(path = "/clientsView")
@Controller
public class ViewUserController {

    private UserService userService;

    @Autowired
    public ViewUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String allUsers(Model model, String keyword) throws Exception{
        if(keyword != null){
            model.addAttribute("allUsers", userService.findUserByKeyword(keyword));
        }else {
            findPaginated(1, "firstName", "asc", model);
        }

        return "clientsView";
    }
    @GetMapping("/showNewEmployeeForm/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) throws Exception {

        User user = userService.getById(id);

        model.addAttribute("userForm", user);
        return "updateuser";
    }
    
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userForm") User user) throws Exception {
        userService.update(user);
        return "redirect:/clientsView"; // Redirect to the user list page
    }

    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value = "id") long id) throws Exception {

        this.userService.delete(id);
        return "redirect:/clientsView";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5; // Fixed number of items per page

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            // Add the list of users to the model
        model.addAttribute("allUsers", listUsers);
        return "clientsView";
    }

}
