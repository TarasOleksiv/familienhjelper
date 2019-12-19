package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Beneficiary;
import ua.petros.service.BeneficiaryService;
import ua.petros.service.StatusService;
import ua.petros.validator.BeneficiaryValidator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private BeneficiaryValidator beneficiaryValidator;

    // Show all beneficiaries
    @RequestMapping(value = "/beneficiaries", method = {RequestMethod.GET})
    public String showBeneficiaries(Model model){
        model.addAttribute("list",beneficiaryService.getAll());
        return "beneficiariesList";
    }

    //Show beneficiary
    @RequestMapping(value = "/beneficiaries/{beneficiaryId}", method = {RequestMethod.GET})
    public String showBeneficiary(Model model, @PathVariable("beneficiaryId") String beneficiaryId){
        model.addAttribute("beneficiary",beneficiaryService.getById(UUID.fromString(beneficiaryId)));
        return "beneficiaryShow";
    }

    // Show form to create new beneficiary
    @RequestMapping(value = "/beneficiaries/new", method = {RequestMethod.GET})
    public String showNewBeneficiaryForm(Model model){
        model.addAttribute("listStatuses",statusService.getAll());
        return "beneficiaryNew";
    }

    // Create beneficiary
    @RequestMapping(value = "/beneficiaries", method = {RequestMethod.POST})
    public String addUser(Model model,
                          @ModelAttribute("name") String name,
                          @ModelAttribute("family") String family,
                          @ModelAttribute("description") String description,
                          @ModelAttribute("income") String income,
                          @ModelAttribute("datefield") String datefield,
                          @ModelAttribute("statusName") String statusName
                          ){

        // prepare beneficiary info
        Beneficiary beneficiary = new Beneficiary();
        UUID uuid = UUID.randomUUID();
        beneficiary.setId(uuid);
        beneficiary.setName(name);
        beneficiary.setFamily(family);
        beneficiary.setDescription(description);
        if (income != null && !income.trim().isEmpty()) {
            beneficiary.setIncome(new BigDecimal(income));
        }
        Date date= null;
        if (datefield != null && !datefield.trim().isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(datefield);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        beneficiary.setDatefield(date);
        beneficiary.setStatus(statusService.findByName(statusName));

        // validate beneficiary
        Map<String, String> messages = beneficiaryValidator.validate(beneficiary);

        // If no errors, create beneficiary
        if (messages.isEmpty()) {
            beneficiaryService.save(beneficiary);
        } else {
            // back to the new beneficiary form
            model.addAttribute("messages", messages);
            model.addAttribute("beneficiary",beneficiary);
            model.addAttribute("listStatuses",statusService.getAll());
            return "beneficiaryNew";
        }

        //show beneficiary
        return "redirect:/beneficiaries/" + uuid;
    }

    // delete beneficiary
    @RequestMapping(value = "/beneficiaries/{beneficiaryId}", method = RequestMethod.DELETE)
    public String deleteBeneficiary(Model model, @ModelAttribute("beneficiaryId") String beneficiaryId){

        if (!beneficiaryId.trim().isEmpty()) {
            beneficiaryService.delete(beneficiaryService.getById(UUID.fromString(beneficiaryId)));
        }
        // Return all beneficiaries
        return "redirect:/beneficiaries";
    }

    // Show form to edit beneficiary
    @RequestMapping(value = "/beneficiaries/{beneficiaryId}/edit", method = {RequestMethod.GET})
    public String showEditBeneficiaryForm(Model model, @PathVariable("beneficiaryId") String beneficiaryId){
        model.addAttribute("beneficiary",beneficiaryService.getById(UUID.fromString(beneficiaryId)));
        model.addAttribute("listStatuses",statusService.getAll());
        return "beneficiaryEdit";
    }

    // Edit beneficiary
    @RequestMapping(value = "/beneficiaries/{beneficiaryId}", method = RequestMethod.PUT)
    public String editBeneficiary(Model model,
                                  @ModelAttribute("name") String name,
                                  @ModelAttribute("family") String family,
                                  @ModelAttribute("description") String description,
                                  @ModelAttribute("income") String income,
                                  @ModelAttribute("datefield") String datefield,
                                  @ModelAttribute("statusName") String statusName,
                                  @ModelAttribute("beneficiaryId") String beneficiaryId){

        // prepare beneficiary info
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(UUID.fromString(beneficiaryId));
        beneficiary.setName(name);
        beneficiary.setFamily(family);
        beneficiary.setDescription(description);
        if (income != null && !income.trim().isEmpty()) {
            beneficiary.setIncome(new BigDecimal(income));
        }
        Date date= null;
        if (datefield != null && !datefield.trim().isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(datefield);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        beneficiary.setDatefield(date);
        beneficiary.setStatus(statusService.findByName(statusName));

        // validate beneficiary
        Map<String, String> messages = beneficiaryValidator.validate(beneficiary);

        // If no errors, update beneficiary
        if (messages.isEmpty()) {
            beneficiaryService.save(beneficiary);
        } else {
            // back to the new beneficiary form
            model.addAttribute("messages", messages);
            model.addAttribute("beneficiary",beneficiary);
            model.addAttribute("listStatuses",statusService.getAll());
            return "beneficiaryEdit";
        }

        //show beneficiary
        return "redirect:/beneficiaries/" + beneficiaryId;
    }
}
