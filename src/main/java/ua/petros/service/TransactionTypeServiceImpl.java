package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.petros.dao.TransactionTypeDao;
import ua.petros.model.TransactionType;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 02.01.2020.
 */
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeDao transactionTypeDao;

    @Override
    public TransactionType getById(UUID id) {
        return transactionTypeDao.getOne(id);
    }

    @Override
    public List<TransactionType> getAll() {
        return transactionTypeDao.findAll();
    }

    @Override
    public void save(TransactionType transactionType) {
        transactionTypeDao.save(transactionType);
    }

    @Override
    public void delete(TransactionType transactionType) {
        transactionTypeDao.delete(transactionType);
    }

    @Override
    public TransactionType findByName(String name) {
        return transactionTypeDao.findByName(name);
    }
}
