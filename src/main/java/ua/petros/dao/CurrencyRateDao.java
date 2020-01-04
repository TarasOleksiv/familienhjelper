package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.petros.model.CurrencyRate;

import java.util.List;
import java.util.UUID;

public interface CurrencyRateDao extends JpaRepository<CurrencyRate, UUID> {

    CurrencyRate findByTargetCurrency(String targetCurrency);

}
