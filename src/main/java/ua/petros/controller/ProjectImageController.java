package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.Image;
import ua.petros.service.ImageService;
import ua.petros.service.ProjectService;
import ua.petros.validator.ImageValidator;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Controller
public class ProjectImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ImageValidator imageValidator;

    // Show all images for particular project
    @RequestMapping(value = "/projects/{projectId}/images", method = {RequestMethod.GET})
    public String showProjectImages(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("listImages",imageService.findByProjectId(UUID.fromString(projectId)));
        return "projectImagesList";
    }

    // Show particular image for particular project
    @RequestMapping(value = "/projects/{projectId}/images/{imageId}", method = {RequestMethod.GET})
    public String showProjectImage(Model model,
                                   @PathVariable("projectId") String projectId,
                                   @PathVariable("imageId") String imageId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("image",imageService.getById(UUID.fromString(imageId)));
        return "projectImageShow";
    }

    // Show form to add new image
    @RequestMapping(value = "/projects/{projectId}/images/new", method = {RequestMethod.GET})
    public String showNewImageForm(Model model, @PathVariable("projectId") String projectId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        return "projectImageNew";
    }

    // Add image to project
    @RequestMapping(value = "/projects/{projectId}/images", method = {RequestMethod.POST})
    public String addImageToProject(Model model,
                                    @PathVariable("projectId") String projectId,
                                    @ModelAttribute("description") String description,
                                    @ModelAttribute("link") String link
    ){
        // prepare info
        Image image = new Image();
        UUID uuid = UUID.randomUUID();
        image.setId(uuid);
        image.setProject(projectService.getById(UUID.fromString(projectId)));
        image.setDescription(description);
        image.setLink(link);

        // validate image
        Map<String, String> messages = imageValidator.validate(image);

        // If no errors, add image link
        if (messages.isEmpty()) {
            imageService.save(image);
        } else {
            // back to the new image form
            model.addAttribute("messages", messages);
            model.addAttribute("image",image);
            model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
            return "projectImageNew";
        }

        return "redirect:/projects/" + projectId + "/images";
    }

    // Remove image from project
    @RequestMapping(value = "/projects/{projectId}/images/{imageId}", method = {RequestMethod.DELETE})
    public String removeImageFromProject(Model model,
                                         @PathVariable("projectId") String projectId,
                                         @PathVariable("imageId") String imageId){
        imageService.delete(imageService.getById(UUID.fromString(imageId)));
        return "redirect:/projects/" + projectId + "/images";
    }

    // Show form to edit image
    @RequestMapping(value = "/projects/{projectId}/images/{imageId}/edit", method = {RequestMethod.GET})
    public String showEditMemberForm(Model model,
                                     @PathVariable("projectId") String projectId,
                                     @PathVariable("imageId") String imageId){
        model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
        model.addAttribute("image",imageService.getById(UUID.fromString(imageId)));
        return "projectImageEdit";
    }

    // Edit image
    @RequestMapping(value = "/projects/{projectId}/images/{imageId}", method = {RequestMethod.PUT})
    public String editImage(Model model,
                                    @PathVariable("projectId") String projectId,
                                    @ModelAttribute("description") String description,
                                    @ModelAttribute("link") String link,
                                    @ModelAttribute("imageId") String imageId
    ){
        // prepare info
        Image image = new Image();
        image.setId(UUID.fromString(imageId));
        image.setProject(projectService.getById(UUID.fromString(projectId)));
        image.setDescription(description);
        image.setLink(link);

        // validate image
        Map<String, String> messages = imageValidator.validate(image);

        // If no errors, add image link
        if (messages.isEmpty()) {
            imageService.save(image);
        } else {
            // back to the new image form
            model.addAttribute("messages", messages);
            model.addAttribute("image",image);
            model.addAttribute("project",projectService.getById(UUID.fromString(projectId)));
            return "projectImageEdit";
        }

        return "redirect:/projects/" + projectId + "/images/" + imageId;
    }

}
