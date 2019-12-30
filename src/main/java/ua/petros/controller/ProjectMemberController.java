package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.ProjectMember;
import ua.petros.service.MemberService;
import ua.petros.service.ProjectMemberService;
import ua.petros.service.ProjectService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    // Show all donors for particular project
    @RequestMapping(value = "/projects/{projectId}/donors", method = {RequestMethod.GET})
    public String showProjectDonors(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("listDonors",projectMemberService.findByProjectId(UUID.fromString(projectId)));
        return "projectMembersList";
    }

    // Show form to add new donor
    @RequestMapping(value = "/projects/{projectId}/donors/new", method = {RequestMethod.GET})
    public String showNewMemberForm(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("listMembers",memberService.getAll());
        return "projectMemberNew";
    }

    // Add donor to project
    @RequestMapping(value = "/projects/{projectId}/donors", method = {RequestMethod.POST})
    public String addDonorToProject(Model model,
                                    @PathVariable("projectId") String projectId,
                                    @ModelAttribute("memberId") String memberId,
                                    @ModelAttribute("pledge") String pledge,
                                    @ModelAttribute("startPledge") String startPledge,
                                    @ModelAttribute("stopPledge") String stopPledge
                                    ){
        // prepare info
        ProjectMember projectMember = new ProjectMember();
        UUID uuid = UUID.randomUUID();
        projectMember.setId(uuid);
        projectMember.setProject(projectService.getById(UUID.fromString(projectId)));
        projectMember.setMember(memberService.getById(UUID.fromString(memberId)));
        if (pledge != null && !pledge.trim().isEmpty()) {
            projectMember.setPledge(new BigDecimal(pledge));
        }
        Date date= null;
        if (startPledge != null && !startPledge.trim().isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(startPledge);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        projectMember.setStartPledge(date);

        if (stopPledge != null && !stopPledge.trim().isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(stopPledge);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        projectMember.setStopPledge(date);

        projectMemberService.save(projectMember);

        return "redirect:/projects/" + projectId + "/donors";
    }

    // Remove donor from project
    @RequestMapping(value = "/projects/{projectId}/donors/{projectMemberId}/delete", method = {RequestMethod.GET})
    public String removeDonorFromProject(Model model,
                                         @PathVariable("projectId") String projectId,
                                         @PathVariable("projectMemberId") String projectMemberId){
        projectMemberService.delete(projectMemberService.getById(UUID.fromString(projectMemberId)));
        return "redirect:/projects/" + projectId + "/donors";
    }

}
