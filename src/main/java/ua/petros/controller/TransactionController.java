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
import ua.petros.validator.BalanceValidator;
import ua.petros.validator.TransactionValidator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
    private BeneficiaryService beneficiaryService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private BalanceValidator balanceValidator;

    private User userPrincipal;
    private List<Beneficiary> beneficiaries;
    private List<Member> members = new ArrayList<>();

    // Show all transactions for particular project
    @RequestMapping(value = "/projects/{projectId}/transactions", method = {RequestMethod.GET})
    public String showTransactions(Model model, @PathVariable("projectId") String projectId){
        Project project = projectService.getById(UUID.fromString(projectId));
        BigDecimal projectBalance = (project.getBalance() == null? new BigDecimal(0): project.getBalance());
        BigDecimal transactionsBalance = balanceValidator.recalculateBalance(project);
        if (projectBalance.compareTo(transactionsBalance) != 0){
            project.setBalance(transactionsBalance);
            projectService.save(project);
        }

        model.addAttribute("project",project);
        model.addAttribute("listTransactions",transactionService.findByProjectId(UUID.fromString(projectId)));
        return "transactionsList";
    }

    // Show particular transaction for particular project
    @RequestMapping(value = "/projects/{projectId}/transactions/{transactionId}", method = {RequestMethod.GET})
    public String showTransaction(Model model,
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
        model.addAttribute("listMembers", members);
        model.addAttribute("listBeneficiaries", beneficiaries);
        List<TransactionType> transactionTypes = transactionTypeService.getAll();
        transactionTypes.sort(Comparator.comparing(TransactionType::getIsDonation).reversed());
        model.addAttribute("listTransactionTypes", transactionTypes);
        model.addAttribute("isIncome", isIncome);
        model.addAttribute("project", projectService.getById(UUID.fromString(projectId)));
        return "transactionNew";
    }

    // Create new transaction
    @RequestMapping(value = "/projects/{projectId}/transactions", method = {RequestMethod.POST})
    public String createNewTransaction(Model model, Authentication authentication,
                                       @PathVariable("projectId") String projectId,
                                       @ModelAttribute("isIncome") String isIncome,
                                       @ModelAttribute("description") String description,
                                       @ModelAttribute("tradingDate") String tradingDate,
                                       @ModelAttribute("amount") String amount,
                                       @ModelAttribute("currencyId") String currencyId,
                                       @ModelAttribute("memberId") String memberId,
                                       @ModelAttribute("beneficiaryId") String beneficiaryId,
                                       @ModelAttribute("transactionTypeId") String transactionTypeId){

        // prepare transaction info
        Transaction transaction = new Transaction();
        UUID uuid = UUID.randomUUID();
        transaction.setId(uuid);
        boolean isIn = ("true".equals(isIncome));
        transaction.setIsIncome(isIn);
        transaction.setDescription(description);
        BigDecimal amountBigDecimal = new BigDecimal(0);
        if (amount != null && !amount.trim().isEmpty()) {
            amountBigDecimal = new BigDecimal(amount);
            transaction.setAmount(new BigDecimal(amount));
        }
        Date date= null;
        if (tradingDate != null && !tradingDate.trim().isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(tradingDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        transaction.setTradingDate(date);
        if (currencyId != null && !currencyId.trim().isEmpty()) {
            transaction.setCurrency(currencyService.getById(UUID.fromString(currencyId)));
        }
        if (transactionTypeId != null && !transactionTypeId.trim().isEmpty()) {
            transaction.setTransactionType(transactionTypeService.getById(UUID.fromString(transactionTypeId)));
        }
        Project project = projectService.getById(UUID.fromString(projectId));
        if (beneficiaryId != null && !beneficiaryId.trim().isEmpty()) {
            transaction.setBeneficiary(beneficiaryService.getById(UUID.fromString(beneficiaryId)));
        }
        if (memberId != null && !memberId.trim().isEmpty()) {
            transaction.setMember(memberService.getById(UUID.fromString(memberId)));
        }

        // validate transaction
        Map<String, String> messages = transactionValidator.validate(transaction);

        // If no errors, create transaction and change project balance
        if (messages.isEmpty()) {
            String targetCurrency = currencyService.getById(UUID.fromString(currencyId)).getName();
            BigDecimal rate = currencyRateService.findByTargetCurrency(targetCurrency).getRate();
            BigDecimal amountNOKBigDecimal = amountBigDecimal.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            transaction.setAmountNOK(amountNOKBigDecimal);

            Project projectUpdated = setProjectBalance(project,amountNOKBigDecimal,isIn);
            transaction.setProject(projectUpdated);
            projectService.save(projectUpdated);
            transactionService.save(transaction);
        } else {
            // back to the new transaction form
            initializeModelAttributes(authentication, projectId);
            model.addAttribute("userPrincipal", userPrincipal);
            model.addAttribute("messages", messages);
            model.addAttribute("transaction", transaction);
            model.addAttribute("listCurrency", currencyService.getAll());
            model.addAttribute("listMembers", members);
            model.addAttribute("listBeneficiaries", beneficiaries);
            List<TransactionType> transactionTypes = transactionTypeService.getAll();
            transactionTypes.sort(Comparator.comparing(TransactionType::getIsDonation).reversed());
            model.addAttribute("listTransactionTypes", transactionTypes);
            model.addAttribute("isIncome", isIncome);
            model.addAttribute("project", projectService.getById(UUID.fromString(projectId)));
            return "transactionNew";
        }

        //show transaction
        return "redirect:/projects/" + projectId + "/transactions/" + uuid;
    }

    // Show form to edit transaction
    @RequestMapping(value = "/projects/{projectId}/transactions/{transactionId}/edit", method = {RequestMethod.GET})
    public String showEditTransactionForm(Model model,
                                         Authentication authentication,
                                         @PathVariable("projectId") String projectId,
                                         @PathVariable("transactionId") String transactionId
                                         ) {
        model.addAttribute("transaction", transactionService.getById(UUID.fromString(transactionId)));
        List<TransactionType> transactionTypes = transactionTypeService.getAll();
        transactionTypes.sort(Comparator.comparing(TransactionType::getIsDonation).reversed());
        model.addAttribute("listTransactionTypes", transactionTypes);
        model.addAttribute("project", projectService.getById(UUID.fromString(projectId)));
        return "transactionEdit";
    }

    // Edit transaction
    @RequestMapping(value = "/projects/{projectId}/transactions/{transactionId}", method = {RequestMethod.PUT})
    public String editTransaction(Model model,
                                          Authentication authentication,
                                          @PathVariable("projectId") String projectId,
                                          @PathVariable("transactionId") String transactionId,
                                          @ModelAttribute("description") String description,
                                          @ModelAttribute("transactionTypeId") String transactionTypeId
                                        ) {
        Transaction transaction = transactionService.getById(UUID.fromString(transactionId));
        transaction.setDescription(description);
        transaction.setTransactionType(transactionTypeService.getById(UUID.fromString(transactionTypeId)));
        transactionService.save(transaction);
        //show transaction
        return "redirect:/projects/" + projectId + "/transactions/" + transactionId;
    }

    // Remove transaction from project
    @RequestMapping(value = "/projects/{projectId}/transactions/{transactionId}", method = {RequestMethod.DELETE})
    public String removeTransactionFromProject(Model model,
                                         @PathVariable("projectId") String projectId,
                                         @PathVariable("transactionId") String transactionId){
        Transaction transaction = transactionService.getById(UUID.fromString(transactionId));
        BigDecimal amountNOK = transaction.getAmountNOK();
        boolean isIn = !transaction.getIsIncome();
        Project project = setProjectBalance(projectService.getById(UUID.fromString(projectId)),amountNOK,isIn);
        projectService.save(project);
        transactionService.delete(transaction);
        return "redirect:/projects/" + projectId + "/transactions";
    }

    // Generate lists to be passed to model views
    private void initializeModelAttributes(Authentication authentication, String projectId){
        String currentPrincipalName = authentication.getName();
        userPrincipal = userService.findByUsername(currentPrincipalName);

        List<Beneficiary> listBeneficiary = beneficiaryService.getAll();
        List<Beneficiary> listBeneficiaryNotNullUser = listBeneficiary.stream()
                .filter(beneficiary -> beneficiary.getUser() != null)
                .collect(Collectors.toList());
        User fieldContactUser = projectService.getById(UUID.fromString(projectId)).getFieldContactUser();
        if (listBeneficiaryNotNullUser != null && fieldContactUser != null){
            final String fcuUsername = fieldContactUser.getUsername();
            beneficiaries = listBeneficiaryNotNullUser.stream()
                    .filter(beneficiary -> fcuUsername.equals(beneficiary.getUser().getUsername()))
                    .sorted(Comparator.comparing(Beneficiary::getName))
                    .collect(Collectors.toList());
        } else {
            beneficiaries = null;
        }

        Set<ProjectMember> listMembers = projectService.getById(UUID.fromString(projectId)).getProjectMembers();
        // Get distinct objects by key
        List<ProjectMember> listMembersUnique = listMembers.stream()
                .filter( distinctByKey(p -> p.getMember()) )
                .collect( Collectors.toList() );

        members.clear();
        for (ProjectMember projectMember: listMembersUnique){
            members.add(projectMember.getMember());
        }
        members.sort(Comparator.comparing(Member::getName));
    }

    //Utility function to get distinct objects by key
    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    // Utility to modify project's balance according to transaction
    private Project setProjectBalance(Project project, BigDecimal amount, boolean isIn ){
        BigDecimal balance = (project.getBalance() == null? new BigDecimal(0): project.getBalance());
        if (isIn){
            balance = balance.add(amount);
        } else {
            balance = balance.subtract(amount);
        }
        project.setBalance(balance);
        return project;
    }
}
