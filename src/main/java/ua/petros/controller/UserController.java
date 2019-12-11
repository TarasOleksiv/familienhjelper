package ua.petros.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import ua.petros.dao.RoleDao;
import ua.petros.model.Role;
import ua.petros.model.User;
import ua.petros.service.RoleService;
import ua.petros.service.SecurityService;
import ua.petros.service.UserService;
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
    private RoleService roleService;

    @Autowired
    private RoleDao roleDao;

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

        userService.create(userForm);

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

    // Show form to create new user
    @RequestMapping(value = "/users/new", method = {RequestMethod.GET})
    public String showNewUserForm(Model model){
        model.addAttribute("listRoles",roleService.getAll());
        return "userNew";
    }

    // Create user
    @RequestMapping(value = "/users", method = {RequestMethod.POST})
    public String addUser(Model model,
                          @ModelAttribute("Cancel") String cancelButton,
                          @ModelAttribute("username") String username,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("mobile1") String mobile1,
                          @ModelAttribute("mobile2") String mobile2,
                          @ModelAttribute("bank") String bank,
                          @ModelAttribute("address") String address,
                          @ModelAttribute("account") String account,
                          @ModelAttribute("roleName") String roleName,
                          @ModelAttribute("password") String password){

        // Cancel button - Return all users
        if(!cancelButton.trim().isEmpty()) {
            return "redirect:/users";
        }

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();
        model.addAttribute("messages", messages);

        // Check username.
        String name = username;
        if (name == null || name.trim().isEmpty()) {
            messages.put("username", "Please enter username");
        }  else if (userService.findByUsername(name) != null) {
            messages.put("username", "User " + name + " exists already");
        }

        // Check password.
        String pass = password;
        if (pass == null || pass.trim().isEmpty()) {
            messages.put("password", "Please enter password");
        }

        // If no errors, create user
        if (messages.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if (account != null && !account.trim().isEmpty()) {user.setAccount(account); }
            if (address != null && !address.trim().isEmpty()) { user.setAddress(address); }
            if (bank != null && !bank.trim().isEmpty()) { user.setBank(bank); }
            if (mobile1 != null && !mobile1.trim().isEmpty()) { user.setMobile1(mobile1); }
            if (mobile2 != null && !mobile2.trim().isEmpty()) { user.setMobile2(mobile2); }
            if (email != null && !email.trim().isEmpty()) { user.setEmail(email); }
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.findByName(roleName));
            user.setRoles(roles);

            userService.create(user);
        }

        model.addAttribute("list",userService.getAll());
        return "usersList";
    }

    // Show form to edit user
    @RequestMapping(value = "/users/{userId}/edit", method = {RequestMethod.GET})
    public String showEditUserForm(Model model, @PathVariable("userId") String userId){
        model.addAttribute("user",userService.getById(UUID.fromString(userId)));
        model.addAttribute("listRoles",roleService.getAll());
        return "userEdit";
    }

    // Edit user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public String editUser(Model model,
                          @ModelAttribute("Save") String saveButton,
                          @ModelAttribute("Cancel") String cancelButton,
                          @ModelAttribute("username") String username,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("mobile1") String mobile1,
                          @ModelAttribute("mobile2") String mobile2,
                          @ModelAttribute("bank") String bank,
                          @ModelAttribute("address") String address,
                          @ModelAttribute("account") String account,
                          @ModelAttribute("roleName") String roleName,
                          @ModelAttribute("password") String password,
                          @ModelAttribute("userId") String userId){

        // Cancel button - Return all users
        if(!cancelButton.trim().isEmpty()) {
            return "redirect:/users";
        }

        // Save button

        // Error messages to send if any
        Map<String, String> messages = new HashMap<String, String>();
        model.addAttribute("messages", messages);

        // check username for empty
        if (username == null || username.trim().isEmpty()) {
            messages.put("username", "Please enter username");
            // check if new username exists already
        }  else {
            User user = userService.findByUsername(username);
            if ((user != null) && (!user.getId().equals(UUID.fromString(userId)))){
                messages.put("username", "User " + username + " exists already");
            }
        }

        // check password for empty
        if (password == null || password.trim().isEmpty()) {
            messages.put("password", "Please enter password");
        }

        // if no validation errors
        if (messages.isEmpty()) {
            User user = new User();
            user.setId(UUID.fromString(userId));
            user.setUsername(username);
            user.setEmail(email);
            user.setAccount(account);
            user.setMobile1(mobile1);
            user.setMobile2(mobile2);
            user.setAddress(address);
            user.setBank(bank);
            user.setPassword(password);
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(roleName));
            user.setRoles(roles);

            // update user
            userService.update(user);

            model.addAttribute("list",userService.getAll());
            return "usersList";
            // else redirect back to the edit page showing error messages
        } else {
            if (!userId.trim().isEmpty()) {
                model.addAttribute("user",userService.getById(UUID.fromString(userId)));
                model.addAttribute("listRoles",roleService.getAll());
                return "userEdit";
            }
        }

        // Return all users
        return "redirect:/users";
    }

    // delete user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String editUser(Model model, @ModelAttribute("userId") String userId){

        if (!userId.trim().isEmpty()) {
            userService.delete(userService.getById(UUID.fromString(userId)));
        }
        // Return all users
        return "redirect:/users";
    }
}
