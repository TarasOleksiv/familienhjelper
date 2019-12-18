package ua.petros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class ProjectController {

    // Show all projects
    @RequestMapping(value = "/projects", method = {RequestMethod.GET})
    public String showMembers(Model model){
        //model.addAttribute("list",userService.getAll());
        return "projectsList";
    }
}
