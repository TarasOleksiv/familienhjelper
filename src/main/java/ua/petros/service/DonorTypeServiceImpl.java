package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.DonorTypeDao;
import ua.petros.model.DonorType;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 23.12.2019.
 */

@Service
public class DonorTypeServiceImpl implements DonorTypeService {

    @Autowired
    private DonorTypeDao donorTypeDao;

    @Override
    public DonorType getById(UUID id) {
        return donorTypeDao.getOne(id);
    }

    @Override
    public List<DonorType> getAll() {
        return donorTypeDao.findAll();
    }

    @Override
    public void save(DonorType donorType) {
        donorTypeDao.save(donorType);
    }

    @Override
    public void delete(DonorType donorType) {
        donorTypeDao.delete(donorType);
    }

    @Override
    public DonorType findByName(String name) {
        return donorTypeDao.findByName(name);
    }
}
