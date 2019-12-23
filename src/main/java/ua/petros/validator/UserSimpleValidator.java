package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.petros.model.User;
import ua.petros.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class UserSimpleValidator {

    @Autowired
    private UserService userService;

    public Map<String, String> validate(User user, boolean checkPassword){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check username.
        String name = user.getUsername();
        if (name == null || name.trim().isEmpty()) {
            messages.put("username", "Please enter username");
        }  else {
            User userDB = userService.findByUsername(user.getUsername());
            if ((userDB != null) && (!userDB.getId().equals(user.getId()))){
                messages.put("username", "User " + user.getUsername() + " exists already");
            }
        }

        // Check password.
        if (checkPassword){
            String pass = user.getPassword();
            if (pass == null || pass.trim().isEmpty()) {
                messages.put("password", "Please enter password");
            }
        }

        return messages;
    }

    public Map<String, String> validatePassword(User user){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        String pass = user.getPassword();
        if (pass == null || pass.trim().isEmpty()) {
            messages.put("password", "Please enter password");
        }

        String confirmPass = user.getConfirmPassword();
        if (confirmPass == null || confirmPass.trim().isEmpty()) {
            messages.put("confirmPassword", "Please enter password");
        }

        if (confirmPass != null && pass != null && !confirmPass.equals(pass)){
            messages.put("confirmPassword", "Password doesn't match, please try again");
        }

        return messages;
    }


}
