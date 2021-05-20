package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.petros.model.Beneficiary;
import ua.petros.model.Project;
import ua.petros.model.User;
import ua.petros.service.BeneficiaryService;
import ua.petros.service.ProjectService;
import ua.petros.service.UserService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class BeneficiaryValidator {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BalanceValidator balanceValidator;

    public List<Beneficiary> getUserBeneficiaryList(Authentication authentication){
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);

        List<Beneficiary>listBeneficiary = beneficiaryService.getAll();
        if ("ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName())){
            List<Beneficiary>listResultBeneficiary = listBeneficiary.stream()
                    .filter(beneficiary -> (beneficiary.getUser() != null && beneficiary.getUser().getId().equals(user.getId())))
                    .collect(Collectors.toList());
            return listResultBeneficiary;
        }

        return listBeneficiary;

    }

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

    public BigDecimal recalculateTotalDonation(Beneficiary beneficiary){
        BigDecimal totalDonation = new BigDecimal(0);
        List<Project> projectsAll = projectService.getAll();
        List<Project> projects = Optional.ofNullable(projectsAll.stream()
                .filter(p -> (p.getBeneficiary() != null && p.getBeneficiary().getId().equals(beneficiary.getId())))
                .collect(Collectors.toList())).orElse(Collections.emptyList());
        for (Project p: projects){
            totalDonation = totalDonation.add(balanceValidator.recalculateDonation(p));
        }

        return totalDonation;
    }
}
