package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.TransactionType;

import java.util.UUID;

public interface TransactionTypeDao extends JpaRepository<TransactionType, UUID> {
    TransactionType findByName(String name);
}
