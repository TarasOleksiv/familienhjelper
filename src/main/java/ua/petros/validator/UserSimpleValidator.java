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

    public Map<String, String> validate(User user){

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
        String pass = user.getPassword();
        if (pass == null || pass.trim().isEmpty()) {
            messages.put("password", "Please enter password");
        }

        return messages;
    }
}
