package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.petros.model.CurrencyRate;

import java.util.List;
import java.util.UUID;

public interface CurrencyRateDao extends JpaRepository<CurrencyRate, UUID> {

    /*@Query(
            value = "SELECT MAX(tradingDate), MAX(sourcecurrency), MAX(targetCurrency), MAX(rate)\n" +
                    "\tFROM currency_rates\n" +
                    "\tWHERE tradingDate= (SELECT MAX(tradingDate) FROM currency_rates)\n" +
                    "\tGROUP BY tradingDate, sourcecurrency, targetCurrency, rate;",
            nativeQuery = true)
    List<CurrencyRate> getCurrentRates();*/

}
