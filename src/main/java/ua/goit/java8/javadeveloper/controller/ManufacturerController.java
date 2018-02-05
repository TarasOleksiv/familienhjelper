package ua.goit.java8.javadeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java8.javadeveloper.model.Manufacturer;
import ua.goit.java8.javadeveloper.model.Product;
import ua.goit.java8.javadeveloper.service.ManufacturerService;
import ua.goit.java8.javadeveloper.service.ProductService;

import java.util.*;

/**
 * Created by Taras on 26.01.2018.
 */

@Controller
public class ManufacturerController {

    @Autowired(required = true)
    private ManufacturerService manufacturerService;

    @Autowired(required = true)
    private ProductService productService;

    @RequestMapping(value = "/showManufacturers", method = {RequestMethod.GET, RequestMethod.POST})
    public String showManufacturers(ModelMap model,
                                    @ModelAttribute("Edit") String editButton,
                                    @ModelAttribute("Add") String addButton,
                                    @ModelAttribute("Delete") String deleteButton,
                                    @ModelAttribute("name") String manufacturerName,
                                    @ModelAttribute("manufacturerId") String manufacturerId) {

        if(!editButton.trim().isEmpty()) {  //при натисканні на кнопку Edit
            if (!manufacturerId.trim().isEmpty()) {
                model.put("manufacturer",manufacturerService.getById(UUID.fromString(manufacturerId)));
                return "manufacturerEdit";
            }
        }

        if(!addButton.trim().isEmpty()){  //при натисканні на кнопку Add

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            model.put("messages", messages);

            //request.setAttribute("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String name = manufacturerName;
            if (name == null || name.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("name", "Please enter name");
            }

            // Якщо немає помилок, втілюєм бізнес-логіку
            if (messages.isEmpty()) {
                Manufacturer manufacturer = new Manufacturer(); //створюєм екземпляр класу моделі бази даних
                manufacturer.withName(manufacturerName);    //задаєм йому і'мя
                manufacturerService.create(manufacturer);   //створюєм нового виробника
            }
        }

        if(!deleteButton.trim().isEmpty()) {  //при натисканні на кнопку Delete
            if (!manufacturerId.trim().isEmpty()) {
                Manufacturer manufacturer = manufacturerService.getById(UUID.fromString(manufacturerId));
                Set<Product> products = manufacturer.getProducts();
                for (Product product: products){
                    productService.delete(product);
                }
                manufacturerService.delete(manufacturerService.getById(UUID.fromString(manufacturerId)));
            }
        }

        model.put("list",manufacturerService.getAll());
        return "manufacturersList";
    }

    @RequestMapping(value = "/editManufacturer", method = RequestMethod.POST)
    public String editManufacturer(ModelMap model,
                                   @ModelAttribute("Save") String saveButton,
                                   @ModelAttribute("Cancel") String cancelButton,
                                   @ModelAttribute("newName") String manufacturerName,
                                   @ModelAttribute("manufacturerId") String manufacturerId){

        if(!saveButton.trim().isEmpty()) {  //при натисканні на кнопку Save

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            model.put("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String newName = manufacturerName;
            if (newName == null || newName.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("newName", "Please enter name");
            }

            if (messages.isEmpty()) {   // Якщо немає помилок, втілюєм бізнес-логіку
                Manufacturer manufacturer = new Manufacturer(); //створюєм екземпляр класу моделі бази даних
                manufacturer.withId(UUID.fromString(manufacturerId))
                        .withName(manufacturerName);
                manufacturerService.update(manufacturer);   //оновлюєм виробника

                model.put("list",manufacturerService.getAll());
                return "manufacturersList";
            } else {    // Якщо є помилки, повертаєм назад на сторінку редагування
                if (!manufacturerId.trim().isEmpty()) {
                    model.put("manufacturer",manufacturerService.getById(UUID.fromString(manufacturerId)));
                    return "manufacturerEdit";
                }
            }

        }

        if(!cancelButton.trim().isEmpty()) {  //при натисканні на кнопку Cancel
            model.put("list",manufacturerService.getAll());
            return "manufacturersList";
        }

        return "manufacturersList";
    }
}
