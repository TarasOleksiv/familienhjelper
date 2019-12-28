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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class ProjectValidator {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public List<Project> getUserProjectList(Authentication authentication){
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);

        List<Project>listProject = projectService.getAll();
        if ("ROLE_FIELDCONTACT".equals(user.getRoles().iterator().next().getName())){
            List<Project>listResultProject = listProject.stream()
                    .filter(project -> (project.getFieldContactUser() != null && project.getFieldContactUser().getId().equals(user.getId())))
                    .collect(Collectors.toList());
            return listResultProject;
        }

        return listProject;

    }

    public Map<String, String> validate(Project project){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check project name.
        String name = project.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name");
        }  else {
            Project projectDB = projectService.findByName(project.getName());
            if ((projectDB != null) && (!projectDB.getId().equals(project.getId()))){
                messages.put("name", "Project " + project.getName() + " exists already");
            }
        }

        return messages;
    }
}
