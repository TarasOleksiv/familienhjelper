package ua.petros.service;

import ua.petros.model.TransactionType;

import java.util.List;
import java.util.UUID;


public interface TransactionTypeService {

    TransactionType getById(UUID id);

    List<TransactionType> getAll();

    void save(TransactionType transactionType);

    void delete(TransactionType transactionType);

    TransactionType findByName(String name);

}
