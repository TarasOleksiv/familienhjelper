package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Currency;

import java.util.UUID;

public interface CurrencyDao extends JpaRepository<Currency, UUID> {
    Currency findByName(String name);
}
