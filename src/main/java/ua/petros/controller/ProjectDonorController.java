package ua.petros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class ProjectDonorController {

    // Show all donors for particular project
    @RequestMapping(value = "/projects/{projectId}/donors", method = {RequestMethod.GET})
    public String showProjectDonors(Model model, @PathVariable("projectId") String projectId){
        //model.addAttribute("list",userService.getAll());
        return "projectDonorsList";
    }
}
