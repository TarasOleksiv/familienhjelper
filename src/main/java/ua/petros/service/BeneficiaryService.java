package ua.petros.service;

import ua.petros.model.Beneficiary;
import ua.petros.model.Status;

import java.util.List;
import java.util.UUID;


public interface BeneficiaryService {

    Beneficiary getById(UUID id);

    List<Beneficiary> getAll();

    void save(Beneficiary beneficiary);

    void delete(Beneficiary beneficiary);

    Beneficiary findByName(String name);

}
