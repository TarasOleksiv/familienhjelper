package ua.goit.java8.javadeveloper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.UUID;

/**
 * Created by Taras on 04.02.2018.
 */
public interface ProductDao extends JpaRepository<Product,UUID> {
    Product findByName(String name);
}
