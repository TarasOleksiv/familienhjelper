package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.petros.model.Beneficiary;
import ua.petros.service.BeneficiaryService;
import ua.petros.service.StatusService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class BeneficiaryValidator {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private StatusService statusService;

    public Map<String, String> validate(Beneficiary beneficiary){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check beneficiary name.
        String name = beneficiary.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name");
        }  else {
            Beneficiary beneficiaryDB = beneficiaryService.findByName(beneficiary.getName());
            if ((beneficiaryDB != null) && (!beneficiaryDB.getId().equals(beneficiary.getId()))){
                messages.put("name", "Beneficiary " + beneficiary.getName() + " exists already");
            }
        }

        return messages;
    }
}
