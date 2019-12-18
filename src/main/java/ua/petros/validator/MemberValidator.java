package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.petros.model.Member;
import ua.petros.service.MemberService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class MemberValidator {

    @Autowired
    private MemberService memberService;

    public Map<String, String> validate(Member member){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check member name.
        String name = member.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name");
        }  else {
            Member memberDB = memberService.findByName(member.getName());
            if ((memberDB != null) && (!memberDB.getId().equals(member.getId()))){
                messages.put("name", "Member " + member.getName() + " exists already");
            }
        }

        return messages;
    }
}
