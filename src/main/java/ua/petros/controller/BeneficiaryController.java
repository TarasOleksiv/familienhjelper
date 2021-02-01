package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.*;
import ua.petros.service.*;
import ua.petros.validator.BeneficiaryValidator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BeneficiaryValidator beneficiaryValidator;

    // Show all beneficiaries
    @RequestMapping(value = "/beneficiaries", method = {RequestMethod.GET})
    public String showBeneficiaries(Model model, Authentication authentication){
        List<Beneficiary>listBeneficiary = beneficiaryValidator.getUserBeneficiaryList(authentication);
        model.addAttribute("list",listBeneficiary);
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
    public String showNewBeneficiaryForm(Model model, Authentication authentication){
        List<User>listUsers = userService.getAll();
        List<User>resultUsers = listUsers.stream()
                .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());
        List<Currency>listCurrency = currencyService.getAll();
        List<Currency>resultCurrency = listCurrency.stream()
                .filter(currency -> "UAH".equals(currency.getName()) || "RUB".equals(currency.getName()))
                .collect(Collectors.toList());
        String currentPrincipalName = authentication.getName();
        User userPrincipal = userService.findByUsername(currentPrincipalName);
        model.addAttribute("userPrincipal",userPrincipal);
        model.addAttribute("listUsers",resultUsers);
        model.addAttribute("listIncomeTypes",incomeTypeService.getAll());
        model.addAttribute("listCurrency",resultCurrency);
        return "beneficiaryNew";
    }

    // Create beneficiary
    @RequestMapping(value = "/beneficiaries", method = {RequestMethod.POST})
    public String addUser(Model model, Authentication authentication,
                          @ModelAttribute("name") String name,
                          @ModelAttribute("family") String family,
                          @ModelAttribute("description") String description,
                          @ModelAttribute("imageFolderLink") String imageFolderLink,
                          @ModelAttribute("income") String income,
                          @ModelAttribute("datefield") String datefield,
                          @ModelAttribute("mobile") String mobile,
                          @ModelAttribute("incomeTypeId") String incomeTypeId,
                          @ModelAttribute("currencyId") String currencyId,
                          @ModelAttribute("userId") String userId,
                          @ModelAttribute("active") String active
                          ){

        // prepare beneficiary info
        Beneficiary beneficiary = new Beneficiary();
        UUID uuid = UUID.randomUUID();
        beneficiary.setId(uuid);
        beneficiary.setName(name.trim());
        beneficiary.setFamily(family);
        beneficiary.setDescription(description);
        beneficiary.setImageFolderLink(imageFolderLink);
        beneficiary.setMobile(mobile);
        if(!active.trim().isEmpty()) {
            beneficiary.setActive(true);
            }
            else {
            beneficiary.setActive(false);
        }
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
        beneficiary.setIncomeType(incomeTypeService.getById(UUID.fromString(incomeTypeId)));
        beneficiary.setCurrency(currencyService.getById(UUID.fromString(currencyId)));
        if (userId != null && !userId.trim().isEmpty()) {
            beneficiary.setUser(userService.getById(UUID.fromString(userId)));
        }

        // validate beneficiary
        Map<String, String> messages = beneficiaryValidator.validate(beneficiary);

        // If no errors, create beneficiary
        if (messages.isEmpty()) {
            beneficiaryService.save(beneficiary);
        } else {
            // back to the new beneficiary form
            List<User>listUsers = userService.getAll();
            List<User>resultUsers = listUsers.stream()
                    .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                    .collect(Collectors.toList());
            List<Currency>listCurrency = currencyService.getAll();
            List<Currency>resultCurrency = listCurrency.stream()
                    .filter(currency -> "UAH".equals(currency.getName()) || "RUB".equals(currency.getName()))
                    .collect(Collectors.toList());
            String currentPrincipalName = authentication.getName();
            User userPrincipal = userService.findByUsername(currentPrincipalName);
            model.addAttribute("userPrincipal",userPrincipal);
            model.addAttribute("messages", messages);
            model.addAttribute("beneficiary",beneficiary);
            model.addAttribute("listUsers",resultUsers);
            model.addAttribute("listIncomeTypes",incomeTypeService.getAll());
            model.addAttribute("listCurrency",resultCurrency);
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
    public String showEditBeneficiaryForm(Model model, Authentication authentication,
                                          @PathVariable("beneficiaryId") String beneficiaryId){
        model.addAttribute("beneficiary",beneficiaryService.getById(UUID.fromString(beneficiaryId)));
        List<User>listUsers = userService.getAll();
        List<User>resultUsers = listUsers.stream()
                .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());
        List<Currency>listCurrency = currencyService.getAll();
        List<Currency>resultCurrency = listCurrency.stream()
                .filter(currency -> "UAH".equals(currency.getName()) || "RUB".equals(currency.getName()))
                .collect(Collectors.toList());
        String currentPrincipalName = authentication.getName();
        User userPrincipal = userService.findByUsername(currentPrincipalName);
        model.addAttribute("userPrincipal",userPrincipal);
        model.addAttribute("listUsers",resultUsers);
        model.addAttribute("listIncomeTypes",incomeTypeService.getAll());
        model.addAttribute("listCurrency",resultCurrency);
        return "beneficiaryEdit";
    }

    // Edit beneficiary
    @RequestMapping(value = "/beneficiaries/{beneficiaryId}", method = RequestMethod.PUT)
    public String editBeneficiary(Model model, Authentication authentication,
                                  @ModelAttribute("name") String name,
                                  @ModelAttribute("family") String family,
                                  @ModelAttribute("description") String description,
                                  @ModelAttribute("imageFolderLink") String imageFolderLink,
                                  @ModelAttribute("income") String income,
                                  @ModelAttribute("datefield") String datefield,
                                  @ModelAttribute("mobile") String mobile,
                                  @ModelAttribute("incomeTypeId") String incomeTypeId,
                                  @ModelAttribute("currencyId") String currencyId,
                                  @ModelAttribute("userId") String userId,
                                  @ModelAttribute("active") String active,
                                  @ModelAttribute("beneficiaryId") String beneficiaryId){

        // prepare beneficiary info
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(UUID.fromString(beneficiaryId));
        beneficiary.setName(name.trim());
        beneficiary.setFamily(family);
        beneficiary.setDescription(description);
        beneficiary.setImageFolderLink(imageFolderLink);
        beneficiary.setMobile(mobile);
        if(!active.trim().isEmpty()) {
            beneficiary.setActive(true);
        }
        else {
            beneficiary.setActive(false);
        }
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
        beneficiary.setIncomeType(incomeTypeService.getById(UUID.fromString(incomeTypeId)));
        beneficiary.setCurrency(currencyService.getById(UUID.fromString(currencyId)));
        if (userId != null && !userId.trim().isEmpty()) {
            beneficiary.setUser(userService.getById(UUID.fromString(userId)));
        }

        // validate beneficiary
        Map<String, String> messages = beneficiaryValidator.validate(beneficiary);

        // If no errors, update beneficiary
        if (messages.isEmpty()) {
            beneficiaryService.save(beneficiary);
        } else {
            // back to the new beneficiary form
            List<User>listUsers = userService.getAll();
            List<User>resultUsers = listUsers.stream()
                    .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                    .collect(Collectors.toList());
            List<Currency>listCurrency = currencyService.getAll();
            List<Currency>resultCurrency = listCurrency.stream()
                    .filter(currency -> "UAH".equals(currency.getName()) || "RUB".equals(currency.getName()))
                    .collect(Collectors.toList());
            String currentPrincipalName = authentication.getName();
            User userPrincipal = userService.findByUsername(currentPrincipalName);
            model.addAttribute("userPrincipal",userPrincipal);
            model.addAttribute("messages", messages);
            model.addAttribute("beneficiary",beneficiary);
            model.addAttribute("listUsers",resultUsers);
            model.addAttribute("listIncomeTypes",incomeTypeService.getAll());
            model.addAttribute("listCurrency",resultCurrency);
            return "beneficiaryEdit";
        }

        //show beneficiary
        return "redirect:/beneficiaries/" + beneficiaryId;
    }
}
