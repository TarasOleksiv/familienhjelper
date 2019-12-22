package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.IncomeTypeDao;
import ua.petros.model.IncomeType;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Service
public class IncomeTypeServiceImpl implements IncomeTypeService {

    @Autowired
    private IncomeTypeDao incomeTypeDao;

    @Override
    public IncomeType getById(UUID id) {
        return incomeTypeDao.getOne(id);
    }

    @Override
    public List<IncomeType> getAll() {
        return incomeTypeDao.findAll();
    }

    @Override
    public void save(IncomeType incomeType) {
        incomeTypeDao.save(incomeType);
    }

    @Override
    public void delete(IncomeType incomeType) {
        incomeTypeDao.delete(incomeType);
    }

    @Override
    public IncomeType findByName(String name) {
        return incomeTypeDao.findByName(name);
    }
}
