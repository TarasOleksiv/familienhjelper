package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionDao extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByProjectId(UUID projectId);
}
