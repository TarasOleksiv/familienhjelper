package ua.petros.service;

import ua.petros.model.Currency;

import java.util.List;
import java.util.UUID;


public interface CurrencyService {

    Currency getById(UUID id);

    List<Currency> getAll();

    void save(Currency currency);

    void delete(Currency currency);

    Currency findByName(String name);

}
