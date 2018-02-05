package ua.goit.java8.javadeveloper.controller;

import ua.goit.java8.javadeveloper.model.User;
import ua.goit.java8.javadeveloper.service.SecurityService;
import ua.goit.java8.javadeveloper.service.UserService;
import ua.goit.java8.javadeveloper.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

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

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }


    @RequestMapping(value = "/showUsers", method = {RequestMethod.GET,RequestMethod.POST})
    public String showUsers(Model model,
                            @ModelAttribute("userId") String userId,
                            @ModelAttribute("Delete") String deleteButton){

        if(!deleteButton.trim().isEmpty()) {  //при натисканні на кнопку Delete
            if (!userId.trim().isEmpty()) {
                userService.delete(userService.getById(UUID.fromString(userId)));
            }
        }
        model.addAttribute("list",userService.getAll());
        return "usersList";
    }


    @RequestMapping(value = "/addUser", method = {RequestMethod.GET,RequestMethod.POST})
    public String addUser(Model model,
                          @ModelAttribute("username") String username,
                          @ModelAttribute("firstName") String firstName,
                          @ModelAttribute("lastName") String lastName,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("password") String password){

        // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

        // підговка повідомлення.
        Map<String, String> messages = new HashMap<String, String>();
        model.addAttribute("messages", messages);

        // Отримуємо імя та перевіряєм чи воно непорожнє.
        String name = username;
        if (name == null || name.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
            messages.put("username", "Please enter username");
        }

        // Перевіряєм пароль.
        String pass = password;
        if (pass == null || pass.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
            messages.put("password", "Please enter password");
        }

        // Якщо немає помилок, створюємо юзера
        if (messages.isEmpty()) {
            User user = new User(); //створюєм екземпляр класу моделі бази даних
            user.setUsername(username);
            user.setPassword(password);
            if (firstName != null && !firstName.trim().isEmpty()) { user.setFirstName(firstName); }
            if (lastName != null && !lastName.trim().isEmpty()) { user.setLastName(lastName); }
            if (email != null && !email.trim().isEmpty()) { user.setEmail(email); }
            userService.create(user);   //створюєм нового юзера
        }

        model.addAttribute("list",userService.getAll());
        return "usersList";
    }
}
