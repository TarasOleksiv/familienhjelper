package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Beneficiary;
import ua.petros.model.Member;
import ua.petros.model.User;
import ua.petros.service.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class TransactionController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    private User userPrincipal;
    private List<Beneficiary> beneficiaries;

    // Show all transactions for particular project
    @RequestMapping(value = "/projects/{projectId}/transactions", method = {RequestMethod.GET})
    public String showMembers(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("listTransactions",transactionService.findByProjectId(UUID.fromString(projectId)));
        return "transactionsList";
    }

    // Show particular transaction for particular project
    @RequestMapping(value = "/projects/{projectId}/transactions/{transactionId}", method = {RequestMethod.GET})
    public String showProjectImage(Model model,
                                   @PathVariable("projectId") String projectId,
                                   @PathVariable("transactionId") String transactionId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("transaction",transactionService.getById(UUID.fromString(transactionId)));
        return "transactionShow";
    }

    // Show form to create new transaction
    @RequestMapping(value = "/projects/{projectId}/transactions/new", method = {RequestMethod.GET})
    public String showNewTransactionForm(Model model,
                                         Authentication authentication,
                                         @PathVariable("projectId") String projectId,
                                         @ModelAttribute("isIncome") String isIncome) {
        initializeModelAttributes(authentication, projectId);
        model.addAttribute("userPrincipal", userPrincipal);
        model.addAttribute("listCurrency", currencyService.getAll());
        model.addAttribute("listMembers", projectService.getById(UUID.fromString(projectId)).getProjectMembers());
        model.addAttribute("listBeneficiaries", beneficiaries);
        model.addAttribute("listTransactionTypes", transactionTypeService.getAll());
        model.addAttribute("isIncome", isIncome);
        model.addAttribute("projectId", projectId);
        return "transactionNew";
    }

    // Generate lists to be passed to model views
    private void initializeModelAttributes(Authentication authentication, String projectId){
        String currentPrincipalName = authentication.getName();
        userPrincipal = userService.findByUsername(currentPrincipalName);

        List<Beneficiary> listBeneficiary = beneficiaryService.getAll();

        /*for (Beneficiary beneficiary: listBeneficiary) {
            if (beneficiary.getUser() == null){
                listBeneficiary.remove(beneficiary);
            }
        }*/

        /*cars.stream()
                .filter(Objects::nonNull)
                .map(Car::getName)        // Assume the class name for car is Car
                .filter(Objects::nonNull)
                .filter(carName -> carName.startsWith("M"))
                .collect(Collectors.toList());*/

        User fieldContactUser = projectService.getById(UUID.fromString(projectId)).getFieldContactUser();
        /*final String fcuUsername = fieldContactUser.getUsername();
        beneficiaries = listBeneficiary.stream()
                .filter(beneficiary -> fcuUsername.equals(beneficiary.getUser().getUsername()))
                .collect(Collectors.toList());*/
        beneficiaries = listBeneficiary;
    }
}
