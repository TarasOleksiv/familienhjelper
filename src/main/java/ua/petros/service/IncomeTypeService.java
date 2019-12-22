package ua.petros.service;

import ua.petros.model.IncomeType;

import java.util.List;
import java.util.UUID;


public interface IncomeTypeService {

    IncomeType getById(UUID id);

    List<IncomeType> getAll();

    void save(IncomeType incomeType);

    void delete(IncomeType incomeType);

    IncomeType findByName(String name);

}
