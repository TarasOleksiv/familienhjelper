package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.TransactionDao;
import ua.petros.model.Transaction;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public Transaction getById(UUID id) {
        return transactionDao.getOne(id);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionDao.findAll();
    }

    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionDao.delete(transaction);
    }

    @Override
    public List<Transaction> findByProjectId(UUID projectId) {
        return transactionDao.findByProjectId(projectId);
    }
}
