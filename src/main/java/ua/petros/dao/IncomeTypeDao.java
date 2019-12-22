package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.IncomeType;

import java.util.UUID;

public interface IncomeTypeDao extends JpaRepository<IncomeType, UUID> {
    IncomeType findByName(String name);
}
