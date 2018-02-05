package ua.goit.java8.javadeveloper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.UUID;

/**
 * Created by Taras on 03.02.2018.
 */
public interface ManufacturerDao extends JpaRepository<Manufacturer,UUID> {
    Manufacturer findByName(String name);
}
