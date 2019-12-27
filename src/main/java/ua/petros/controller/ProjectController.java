package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Currency;
import ua.petros.model.Status;
import ua.petros.model.User;
import ua.petros.service.ProjectService;
import ua.petros.service.StatusService;
import ua.petros.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    // Show all projects
    @RequestMapping(value = "/projects", method = {RequestMethod.GET})
    public String showMembers(Model model){
        model.addAttribute("list",projectService.getAll());
        return "projectsList";
    }

    //Show project
    @RequestMapping(value = "/projects/{projectId}", method = {RequestMethod.GET})
    public String showProject(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        return "projectShow";
    }

    // Show form to create new project
    @RequestMapping(value = "/projects/new", method = {RequestMethod.GET})
    public String showNewProjectForm(Model model, Authentication authentication){
        List<User> listUsers = userService.getAll();
        List<User>fieldContactUsers = listUsers.stream()
                .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());
        List<User>fuUsers = listUsers.stream()
                .filter(user -> "ROLE_FU".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());

        String currentPrincipalName = authentication.getName();
        User userPrincipal = userService.findByUsername(currentPrincipalName);
        model.addAttribute("userPrincipal",userPrincipal);
        model.addAttribute("listFieldContactUsers",fieldContactUsers);
        model.addAttribute("listFuUsers",fuUsers);
        model.addAttribute("listStatuses",statusService.getAll());
        return "projectNew";
    }

}
