package ua.petros.service;

import ua.petros.model.Image;
import ua.petros.model.Transaction;

import java.util.List;
import java.util.UUID;


public interface TransactionService {

    Transaction getById(UUID id);

    List<Transaction> getAll();

    void save(Transaction transaction);

    void delete(Transaction transaction);

    List<Transaction> findByProjectId(UUID projectId);
}
