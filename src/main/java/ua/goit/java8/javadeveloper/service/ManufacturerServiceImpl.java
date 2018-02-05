package ua.goit.java8.javadeveloper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.java8.javadeveloper.dao.ManufacturerDao;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 03.02.2018.
 */

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer getById(UUID id) {
        return manufacturerDao.getOne(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.findAll();
    }

    @Override
    public void create(Manufacturer manufacturer) {
        manufacturerDao.save(manufacturer);
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        manufacturerDao.delete(manufacturer);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        manufacturerDao.save(manufacturer);
    }

    @Override
    public Manufacturer findByName(String name) {
        return manufacturerDao.findByName(name);
    }

}
