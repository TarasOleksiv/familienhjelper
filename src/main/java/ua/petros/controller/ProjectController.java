package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Currency;
import ua.petros.model.CurrencyRate;
import ua.petros.model.Project;
import ua.petros.model.User;
import ua.petros.service.*;
import ua.petros.validator.BalanceValidator;
import ua.petros.validator.ProjectValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private ProjectValidator projectValidator;

    @Autowired
    private BalanceValidator balanceValidator;

    private User userPrincipal;
    private List<User> fieldContactUsers;
    private List<User> fuUsers;

    // Show all projects
    @RequestMapping(value = "/projects", method = {RequestMethod.GET})
    public String showProjects(Model model, Authentication authentication) {
        List<Project> listProject = projectValidator.getUserProjectList(authentication);
        BigDecimal totalBalance = projectValidator.getTotalBalance(listProject);
        model.addAttribute("list", listProject);
        model.addAttribute("totalBalance",totalBalance);
        return "projectsList";
    }

    //Show project
    @RequestMapping(value = "/projects/{projectId}", method = {RequestMethod.GET})
    public String showProject(Model model, @PathVariable("projectId") String projectId) {
        Project project = projectService.getById(UUID.fromString(projectId));
        BigDecimal projectBalance = (project.getBalance() == null? new BigDecimal(0): project.getBalance());
        BigDecimal transactionsBalance = balanceValidator.recalculateBalance(project);
        if (projectBalance.compareTo(transactionsBalance) != 0){
            project.setBalance(transactionsBalance);
            projectService.save(project);
        }

        Map<String,BigDecimal> currencyBalanceMap = getCurrencyBalanceMap(project);
        model.addAttribute("listCurrency", currencyService.getAll());
        model.addAttribute("currencyBalanceMap",currencyBalanceMap);
        model.addAttribute("project", project);
        return "projectShow";
    }

    // Show form to create new project
    @RequestMapping(value = "/projects/new", method = {RequestMethod.GET})
    public String showNewProjectForm(Model model, Authentication authentication) {
        initializeModelAttributes(authentication);
        model.addAttribute("userPrincipal", userPrincipal);
        model.addAttribute("listFieldContactUsers", fieldContactUsers);
        model.addAttribute("listFuUsers", fuUsers);
        model.addAttribute("listStatuses", statusService.getAll());
        return "projectNew";
    }

    // Create project
    @RequestMapping(value = "/projects", method = {RequestMethod.POST})
    public String addProject(Model model, Authentication authentication,
                             @ModelAttribute("name") String name,
                             @ModelAttribute("description") String description,
                             @ModelAttribute("startDate") String startDate,
                             @ModelAttribute("stopDate") String stopDate,
                             @ModelAttribute("statusName") String statusName,
                             @ModelAttribute("fieldContactId") String fieldContactId,
                             @ModelAttribute("fuId") String fuId,
                             @ModelAttribute("feedback") String feedback,
                             @ModelAttribute("active") String active
    ) {

        // prepare project info
        Project project = new Project();
        UUID uuid = UUID.randomUUID();
        project.setId(uuid);
        project.setName(name);
        project.setDescription(description);
        project.setFeedback(feedback);
        if(!active.trim().isEmpty()) {
            project.setActive(true);
        }
        else {
            project.setActive(false);
        }
        Date dateStart = null;
        if (startDate != null && !startDate.trim().isEmpty()) {
            try {
                dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        project.setStartDate(dateStart);

        Date dateStop = null;
        if (stopDate != null && !stopDate.trim().isEmpty()) {
            try {
                dateStop = new SimpleDateFormat("yyyy-MM-dd").parse(stopDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        project.setStopDate(dateStop);

        project.setStatus(statusService.findByName(statusName));
        if (fieldContactId != null && !fieldContactId.trim().isEmpty()) {
            project.setFieldContactUser(userService.getById(UUID.fromString(fieldContactId)));
        }
        if (fuId != null && !fuId.trim().isEmpty()) {
            project.setFuUser(userService.getById(UUID.fromString(fuId)));
        }

        // validate project
        Map<String, String> messages = projectValidator.validate(project);

        // If no errors, create project
        if (messages.isEmpty()) {
            projectService.save(project);
        } else {
            // back to the new project form
            initializeModelAttributes(authentication);
            model.addAttribute("userPrincipal", userPrincipal);
            model.addAttribute("messages", messages);
            model.addAttribute("project", project);
            model.addAttribute("listFieldContactUsers", fieldContactUsers);
            model.addAttribute("listFuUsers", fuUsers);
            model.addAttribute("listStatuses", statusService.getAll());
            return "projectNew";
        }

        //show project
        return "redirect:/projects/" + uuid;
    }

    // delete project
    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.DELETE)
    public String deleteProject(Model model, @ModelAttribute("projectId") String projectId) {

        if (!projectId.trim().isEmpty()) {
            projectService.delete(projectService.getById(UUID.fromString(projectId)));
        }
        // Return all projects
        return "redirect:/projects";
    }

    // Show form to edit project
    @RequestMapping(value = "/projects/{projectId}/edit", method = {RequestMethod.GET})
    public String showEditProjectForm(Model model, Authentication authentication,
                                      @PathVariable("projectId") String projectId) {
        initializeModelAttributes(authentication);
        model.addAttribute("project", projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("userPrincipal", userPrincipal);
        model.addAttribute("listFieldContactUsers", fieldContactUsers);
        model.addAttribute("listFuUsers", fuUsers);
        model.addAttribute("listStatuses", statusService.getAll());
        return "projectEdit";
    }

    // Edit project
    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.PUT)
    public String editProject(Model model, Authentication authentication,
                             @ModelAttribute("name") String name,
                             @ModelAttribute("description") String description,
                             @ModelAttribute("startDate") String startDate,
                             @ModelAttribute("stopDate") String stopDate,
                             @ModelAttribute("statusName") String statusName,
                             @ModelAttribute("fieldContactId") String fieldContactId,
                             @ModelAttribute("fuId") String fuId,
                             @ModelAttribute("feedback") String feedback,
                             @ModelAttribute("active") String active,
                             @ModelAttribute("projectId") String projectId
    ) {

        // prepare project info
        Project project = new Project();
        project.setId(UUID.fromString(projectId));
        project.setName(name);
        project.setDescription(description);
        project.setFeedback(feedback);
        if(!active.trim().isEmpty()) {
            project.setActive(true);
        }
        else {
            project.setActive(false);
        }
        Date dateStart = null;
        if (startDate != null && !startDate.trim().isEmpty()) {
            try {
                dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        project.setStartDate(dateStart);

        Date dateStop = null;
        if (stopDate != null && !stopDate.trim().isEmpty()) {
            try {
                dateStop = new SimpleDateFormat("yyyy-MM-dd").parse(stopDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        project.setStopDate(dateStop);

        project.setStatus(statusService.findByName(statusName));
        if (fieldContactId != null && !fieldContactId.trim().isEmpty()) {
            project.setFieldContactUser(userService.getById(UUID.fromString(fieldContactId)));
        }
        if (fuId != null && !fuId.trim().isEmpty()) {
            project.setFuUser(userService.getById(UUID.fromString(fuId)));
        }

        // validate project
        Map<String, String> messages = projectValidator.validate(project);

        // If no errors, save project
        if (messages.isEmpty()) {
            projectService.save(project);
        } else {
            // back to the edit project form
            initializeModelAttributes(authentication);
            model.addAttribute("userPrincipal", userPrincipal);
            model.addAttribute("messages", messages);
            model.addAttribute("project", project);
            model.addAttribute("listFieldContactUsers", fieldContactUsers);
            model.addAttribute("listFuUsers", fuUsers);
            model.addAttribute("listStatuses", statusService.getAll());
            return "projectEdit";
        }

        //show project
        return "redirect:/projects/" + projectId;
    }


    // Generate lists to be passed to model views
    private void initializeModelAttributes(Authentication authentication){
        List<User> listUsers = userService.getAll();
        fieldContactUsers = listUsers.stream()
                .filter(user -> "ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());
        fuUsers = listUsers.stream()
                .filter(user -> "ROLE_FU".equals(user.getRoles().iterator().next().getName()))
                .collect(Collectors.toList());

        String currentPrincipalName = authentication.getName();
        userPrincipal = userService.findByUsername(currentPrincipalName);
    }

    // Generate list of currency balance
    private Map<String, BigDecimal> getCurrencyBalanceMap(Project project){
        Map<String, BigDecimal> currencyBalanceMap = new HashMap<>();
        List<Currency> currencyList = currencyService.getAll();
        List<CurrencyRate> currencyRateList = currencyRateService.getAll();
        BigDecimal balance = (project.getBalance() == null? new BigDecimal(0): project.getBalance());

        for (Currency currency: currencyList){
            String targetCurrency = currency.getName();
            BigDecimal rate = currencyRateService.findByTargetCurrency(targetCurrency).getRate();
            BigDecimal currencyBalance = balance.divide(rate,2, RoundingMode.HALF_EVEN);
            currencyBalanceMap.put(targetCurrency,currencyBalance);
        }

        return currencyBalanceMap;
    }
}
