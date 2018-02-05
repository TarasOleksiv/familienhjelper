package ua.goit.java8.javadeveloper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.java8.javadeveloper.dao.ProductDao;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 04.02.2018.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getById(UUID id) {
        return productDao.getOne(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public void create(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Product product) { productDao.delete(product); }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }
}
