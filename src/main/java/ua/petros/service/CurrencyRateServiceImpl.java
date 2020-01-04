package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.CurrencyRateDao;
import ua.petros.model.CurrencyRate;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 02.01.2020.
 */

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    @Autowired
    private CurrencyRateDao currencyRateDao;

    @Override
    public CurrencyRate getById(UUID id) {
        return currencyRateDao.getOne(id);
    }

    @Override
    public List<CurrencyRate> getAll() {
        return currencyRateDao.findAll();
    }

    @Override
    public void save(CurrencyRate currencyRate) {
        currencyRateDao.save(currencyRate);
    }

    @Override
    public void delete(CurrencyRate currencyRate) {
        currencyRateDao.delete(currencyRate);
    }

    @Override
    public CurrencyRate findByTargetCurrency(String targetCurrency) {
        return currencyRateDao.findByTargetCurrency(targetCurrency);
    }

}
