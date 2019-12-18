package ua.petros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class MemberController {

    // Show all members
    @RequestMapping(value = "/members", method = {RequestMethod.GET})
    public String showMembers(Model model){
        //model.addAttribute("list",userService.getAll());
        return "membersList";
    }
}
