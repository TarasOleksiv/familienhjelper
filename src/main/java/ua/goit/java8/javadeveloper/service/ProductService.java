package ua.goit.java8.javadeveloper.service;

import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 04.02.2018.
 */
public interface ProductService {

    Product getById(UUID id);

    List<Product> getAll();

    void create(Product product);

    void delete(Product product);

    void update(Product product);

    Product findByName(String name);
}
