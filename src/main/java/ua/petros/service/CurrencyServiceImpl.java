package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.CurrencyDao;
import ua.petros.dao.StatusDao;
import ua.petros.model.Currency;
import ua.petros.model.Status;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    @Override
    public Currency getById(UUID id) {
        return currencyDao.getOne(id);
    }

    @Override
    public List<Currency> getAll() {
        return currencyDao.findAll();
    }

    @Override
    public void save(Currency currency) {
        currencyDao.save(currency);
    }

    @Override
    public void delete(Currency currency) {
        currencyDao.delete(currency);
    }

    @Override
    public Currency findByName(String name) {
        return currencyDao.findByName(name);
    }
}
