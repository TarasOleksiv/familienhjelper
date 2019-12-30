package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.petros.model.Image;
import ua.petros.model.Member;
import ua.petros.service.ImageService;
import ua.petros.service.MemberService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class ImageValidator {

    @Autowired
    private ImageService imageService;

    public Map<String, String> validate(Image image){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check image link.
        String link = image.getLink();
        if (link == null || link.trim().isEmpty()) {
            messages.put("link", "Please enter link");
        }

        return messages;
    }
}
