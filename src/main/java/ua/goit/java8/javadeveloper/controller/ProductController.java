package ua.goit.java8.javadeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java8.javadeveloper.model.Product;
import ua.goit.java8.javadeveloper.service.ManufacturerService;
import ua.goit.java8.javadeveloper.service.ProductService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 26.01.2018.
 */

@Controller
public class ProductController {

    @Autowired(required = true)
    private ProductService productService;

    @Autowired(required = true)
    private ManufacturerService manufacturerService;

    @RequestMapping(value = "/showProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProducts(ModelMap model,
                               @ModelAttribute("Edit") String editButton,
                               @ModelAttribute("Add") String addButton,
                               @ModelAttribute("Delete") String deleteButton,
                               @ModelAttribute("Show") String showButton,
                               @ModelAttribute("ShowAll") String showAllButton,
                               @ModelAttribute("name") String productName,
                               @ModelAttribute("price") String productPrice,
                               @ModelAttribute("productId") String productId,
                               @ModelAttribute("manufacturerId") String manufacturerId,
                               @ModelAttribute("manufacturerForProductId") String manufacturerForProductId,
                               @ModelAttribute("idManufacturer") String idManufacturer,
                               @ModelAttribute("filterProducts") String filterProducts){
        if(!editButton.trim().isEmpty()) {  //при натисканні на кнопку Edit
            if (!productId.trim().isEmpty()) {
                model.put("product",productService.getById(UUID.fromString(productId)));
                model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
                return "productEdit";
            }
        }

        if(!addButton.trim().isEmpty()){  //при натисканні на кнопку Add

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            model.put("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String name = productName;
            if (name == null || name.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("name", "Please enter name");
            }

            // Перевіряєм ціну.
            String price = productPrice;
            if (price == null || price.trim().isEmpty()) {
                messages.put("price", "Please enter price");
            } else if (!price.matches("^[0-9]*[.]?[0-9]+$")) {
                messages.put("price", "Please enter digits and/or dot only");
            }

            // Якщо немає помилок, втілюєм бізнес-логіку
            if (messages.isEmpty()) {
                Product product = new Product(); //створюєм екземпляр класу моделі бази даних
                product.withName(productName)
                        .withPrice(new BigDecimal(productPrice))
                        .withManufacturer(manufacturerService.getById(UUID.fromString(manufacturerId)));    //задаєм йому і'мя, ціну та виробника
                productService.create(product);   //створюєм новий виріб
            }

        }

        if(!deleteButton.trim().isEmpty()) {  //при натисканні на кнопку Delete
            if (!productId.trim().isEmpty()) {
                productService.delete(productService.getById(UUID.fromString(productId)));
            }
        }

        if(!showAllButton.trim().isEmpty()) {  //при натисканні на кнопку ShowAll показуєм всі вироби

        }

        if(!showButton.trim().isEmpty()) {  //при натисканні на кнопку Show показуєм лише вироби вказаного виробника
            model.put("listProducts",
                    manufacturerService.getById(UUID.fromString(manufacturerForProductId)).getProducts());
            //створюєм атрибут який виводить список всіх виробів вибраного виробника
            model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
            return "productsList";
        }

        if(!filterProducts.trim().isEmpty()) {  //редірект з виробника: показуєм лише вироби вказаного виробника
            model.put("listProducts",
                    manufacturerService.getById(UUID.fromString(idManufacturer)).getProducts());
            //створюєм атрибут який виводить список всіх виробів вибраного виробника
            model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
            return "productsList";
        }

        model.put("listProducts",productService.getAll());  //створюєм атрибут який виводить список всіх виробів
        model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
        return "productsList";
    }


    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String editProduct(ModelMap model,
                              @ModelAttribute("Save") String saveButton,
                              @ModelAttribute("Cancel") String cancelButton,
                              @ModelAttribute("newName") String productName,
                              @ModelAttribute("newPrice") String productPrice,
                              @ModelAttribute("productId") String productId,
                              @ModelAttribute("manufacturerId") String manufacturerId){
        if(!saveButton.trim().isEmpty()) {  //при натисканні на кнопку Save

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            model.put("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String newName = productName;
            if (newName == null || newName.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("newName", "Please enter name");
            }

            // Перевіряєм ціну.
            String newPrice = productPrice;
            if (newPrice == null || newPrice.trim().isEmpty()) {
                messages.put("newPrice", "Please enter price");
            } else if (!newPrice.matches("^[0-9]*[.]?[0-9]+$")) {
                messages.put("newPrice", "Please enter digits and/or dot only");
            }

            if (messages.isEmpty()) {   // Якщо немає помилок, втілюєм бізнес-логіку
                Product product = new Product(); //створюєм екземпляр класу моделі бази даних
                product.withId(UUID.fromString(productId))
                        .withPrice(new BigDecimal(productPrice))
                        .withManufacturer(manufacturerService.getById(UUID.fromString(manufacturerId)))
                        .withName(productName);
                productService.update(product);   //оновлюєм виріб

                model.put("listProducts",productService.getAll());  //створюєм атрибут який виводить список всіх виробів
                model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
                return "productsList";
            } else {
                if (!productId.trim().isEmpty()) {
                    model.put("product",productService.getById(UUID.fromString(productId)));
                    model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
                    return "productEdit";
                }
            }

        }

        if(!cancelButton.trim().isEmpty()) {  //при натисканні на кнопку Cancel
            model.put("listProducts",productService.getAll());  //створюєм атрибут який виводить список всіх виробів
            model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
            return "productsList";
        }

        model.put("listProducts",productService.getAll());  //створюєм атрибут який виводить список всіх виробів
        model.put("listManufacturers",manufacturerService.getAll());  //створюєм атрибут який виводить список всіх виробників
        return "productsList";
    }
}
