package ua.goit.java8.javadeveloper.service;

import ua.goit.java8.javadeveloper.model.Manufacturer;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 03.02.2018.
 */
public interface ManufacturerService {

    Manufacturer getById(UUID id);

    List<Manufacturer> getAll();

    void create(Manufacturer manufacturer);

    void delete(Manufacturer manufacturer);

    void update(Manufacturer manufacturer);

    Manufacturer findByName(String name);

}
