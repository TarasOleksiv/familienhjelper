package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Member;
import ua.petros.service.DonorTypeService;
import ua.petros.service.MemberService;
import ua.petros.validator.MemberValidator;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DonorTypeService donorTypeService;

    @Autowired
    private MemberValidator memberValidator;

    // Show all members
    @RequestMapping(value = "/members", method = {RequestMethod.GET})
    public String showMembers(Model model){
        model.addAttribute("list",memberService.getAll());
        return "membersList";
    }

    //Show member
    @RequestMapping(value = "/members/{memberId}", method = {RequestMethod.GET})
    public String showMember(Model model, @PathVariable("memberId") String memberId){
        model.addAttribute("member",memberService.getById(UUID.fromString(memberId)));
        return "memberShow";
    }

    // Show form to create new member
    @RequestMapping(value = "/members/new", method = {RequestMethod.GET})
    public String showNewMemberForm(Model model){
        model.addAttribute("listDonorTypes",donorTypeService.getAll());
        return "memberNew";
    }

    // Create member
    @RequestMapping(value = "/members", method = {RequestMethod.POST})
    public String addMember(Model model,
                          @ModelAttribute("name") String name,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("mobile") String mobile,
                          @ModelAttribute("bank") String bank,
                          @ModelAttribute("city") String city,
                          @ModelAttribute("account") String account,
                          @ModelAttribute("donorTypeId") String donorTypeId
                          ){

        // prepare member info
        Member member = new Member();
        UUID uuid = UUID.randomUUID();
        member.setId(uuid);
        member.setName(name);
        member.setEmail(email);
        member.setAccount(account);
        member.setMobile(mobile);
        member.setCity(city);
        member.setBank(bank);
        member.setDonorType(donorTypeService.getById(UUID.fromString(donorTypeId)));

        // validate member
        Map<String, String> messages = memberValidator.validate(member);

        // If no errors, create member
        if (messages.isEmpty()) {
            memberService.save(member);
        } else {
            // back to the new member form
            model.addAttribute("messages", messages);
            model.addAttribute("member",member);
            model.addAttribute("listDonorTypes",donorTypeService.getAll());
            return "memberNew";
        }

        //show member
        return "redirect:/members/" + uuid;
    }

    // delete member
    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.DELETE)
    public String deleteMember(Model model, @ModelAttribute("memberId") String memberId){

        if (!memberId.trim().isEmpty()) {
            memberService.delete(memberService.getById(UUID.fromString(memberId)));
        }
        // Return all members
        return "redirect:/members";
    }

    // Show form to edit member
    @RequestMapping(value = "/members/{memberId}/edit", method = {RequestMethod.GET})
    public String showEditMemberForm(Model model, @PathVariable("memberId") String memberId){
        model.addAttribute("member",memberService.getById(UUID.fromString(memberId)));
        model.addAttribute("listDonorTypes",donorTypeService.getAll());
        return "memberEdit";
    }

    // Edit member
    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.PUT)
    public String editMember(Model model,
                           @ModelAttribute("name") String name,
                           @ModelAttribute("email") String email,
                           @ModelAttribute("mobile") String mobile,
                           @ModelAttribute("bank") String bank,
                           @ModelAttribute("account") String account,
                           @ModelAttribute("city") String city,
                           @ModelAttribute("memberId") String memberId,
                           @ModelAttribute("donorTypeId") String donorTypeId
                           ){

        // prepare member info
        Member member = new Member();
        member.setId(UUID.fromString(memberId));
        member.setName(name);
        member.setEmail(email);
        member.setAccount(account);
        member.setMobile(mobile);
        member.setCity(city);
        member.setBank(bank);
        member.setDonorType(donorTypeService.getById(UUID.fromString(donorTypeId)));

        // validate member
        Map<String, String> messages = memberValidator.validate(member);

        // if no validation errors update member
        if (messages.isEmpty()) {
            memberService.save(member);
            // else redirect back to the edit page showing error messages
        } else {
            if (!memberId.trim().isEmpty()) {
                model.addAttribute("messages", messages);
                model.addAttribute("member",member);
                model.addAttribute("listDonorTypes",donorTypeService.getAll());
                return "memberEdit";
            }
        }

        //show member
        return "redirect:/members/" + memberId;
    }

}
