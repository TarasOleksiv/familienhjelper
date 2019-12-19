package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.BeneficiaryDao;
import ua.petros.model.Beneficiary;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryDao beneficiaryDao;

    @Override
    public Beneficiary getById(UUID id) {
        return beneficiaryDao.getOne(id);
    }

    @Override
    public List<Beneficiary> getAll() {
        return beneficiaryDao.findAll();
    }

    @Override
    public void save(Beneficiary beneficiary) {
        beneficiaryDao.save(beneficiary);
    }

    @Override
    public void delete(Beneficiary beneficiary) {
        delete(beneficiary);
    }

    @Override
    public Beneficiary findByName(String name) {
        return beneficiaryDao.findByName(name);
    }
}
