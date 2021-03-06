package ua.petros.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import ua.petros.dao.RoleDao;
import ua.petros.model.Role;
import ua.petros.model.User;
import ua.petros.service.RoleService;
import ua.petros.service.SecurityService;
import ua.petros.service.UserService;
import ua.petros.validator.UserSimpleValidator;
import ua.petros.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserSimpleValidator userSimpleValidator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Registration page
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    // Registration page
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/index";
    }

    // Login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    // landing page
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "index";
    }


    // Show all users
    @RequestMapping(value = "/users", method = {RequestMethod.GET})
    public String showUsers(Model model){
        model.addAttribute("list",userService.getAll());
        return "usersList";
    }

    //Show user
    @RequestMapping(value = "/users/{userId}", method = {RequestMethod.GET})
    public String showUser(Model model, @PathVariable("userId") String userId){
        model.addAttribute("user",userService.getById(UUID.fromString(userId)));
        return "userShow";
    }

    // Show form to create new user
    @RequestMapping(value = "/users/new", method = {RequestMethod.GET})
    public String showNewUserForm(Model model){
        model.addAttribute("listRoles",roleService.getAll());
        return "userNew";
    }

    // Create, edit user
    @RequestMapping(value = {"/users","/users/{userId}"}, method = {RequestMethod.POST})
    public String addUser(Model model,
                          @ModelAttribute("username") String username,
                          @ModelAttribute("firstName") String firstName,
                          @ModelAttribute("lastName") String lastName,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("mobile1") String mobile1,
                          @ModelAttribute("mobile2") String mobile2,
                          @ModelAttribute("bank") String bank,
                          @ModelAttribute("address") String address,
                          @ModelAttribute("account") String account,
                          @ModelAttribute("roleName") String roleName,
                          @ModelAttribute("password") String password,
                          @ModelAttribute("active") String active,
                          @ModelAttribute("userId") String userId){

        // prepare user info
        User user = new User();
        if (userId == null || userId.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            user.setId(uuid);
        } else {
            user.setId(UUID.fromString(userId));
        }
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if (userId == null || userId.isEmpty()) {
            user.setPassword(password);
        }
        user.setEmail(email);
        user.setAccount(account);
        user.setMobile1(mobile1);
        user.setMobile2(mobile2);
        user.setAddress(address);
        user.setBank(bank);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByName(roleName));
        user.setRoles(roles);
        if(!active.trim().isEmpty()) {
            user.setActive(true);
        }
        else {
            user.setActive(false);
        }

        // validate user
        boolean checkPassword = (userId == null || userId.isEmpty());
        Map<String, String> messages = userSimpleValidator.validate(user, checkPassword);

        // If no errors, create user
        if (messages.isEmpty()) {
            if (userId == null || userId.isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode(password));
            }
            userService.save(user);
        } else {
            // back to the new user form
            model.addAttribute("messages", messages);
            model.addAttribute("user",user);
            model.addAttribute("listRoles",roleService.getAll());
            if (userId == null || userId.isEmpty()){
                return "userNew";
            } else {
                return "userEdit";
            }
        }

        //show user
        return "redirect:/users/" + user.getId();
    }

    // Show form to reset password
    @RequestMapping(value = "/users/{userId}/password", method = {RequestMethod.GET})
    public String showResetPasswordForm(Model model, @PathVariable("userId") String userId){
        model.addAttribute("user",userService.getById(UUID.fromString(userId)));
        return "userResetPassword";
    }

    // Change user password
    @RequestMapping(value = "/users/{userId}/password", method = {RequestMethod.POST})
    public String addUser(Model model,
                          @ModelAttribute("password") String password,
                          @ModelAttribute("confirmPassword") String confirmPassword,
                          @ModelAttribute("userId") String userId){

        User user = userService.getById(UUID.fromString(userId));
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);

        // validate user
        Map<String, String> messages = userSimpleValidator.validatePassword(user);

        // If no errors, change password
        if (messages.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userService.save(user);
        } else {
            // back to the reset password form
            model.addAttribute("messages", messages);
            model.addAttribute("user",user);
            return "userResetPassword";
        }

        //show user
        model.addAttribute("user",user);
        model.addAttribute("passwordNotification","Password successfully changed!");
        return "userShow";
    }

    // Show form to edit user
    @RequestMapping(value = "/users/{userId}/edit", method = {RequestMethod.GET})
    public String showEditUserForm(Model model, @PathVariable("userId") String userId){
        model.addAttribute("user",userService.getById(UUID.fromString(userId)));
        model.addAttribute("listRoles",roleService.getAll());
        return "userEdit";
    }

    // delete user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(Model model, @ModelAttribute("userId") String userId){

        if (!userId.trim().isEmpty()) {
            userService.delete(userService.getById(UUID.fromString(userId)));
        }
        // Return all users
        return "redirect:/users";
    }
}
