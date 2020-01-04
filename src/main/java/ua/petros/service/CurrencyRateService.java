package ua.petros.service;

import ua.petros.model.CurrencyRate;

import java.util.List;
import java.util.UUID;


public interface CurrencyRateService {

    CurrencyRate getById(UUID id);

    List<CurrencyRate> getAll();

    void save(CurrencyRate currencyRate);

    void delete(CurrencyRate currencyRate);

    CurrencyRate findByTargetCurrency(String targetCurrency);

}
