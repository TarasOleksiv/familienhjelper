package ua.petros.service;

import ua.petros.model.DonorType;

import java.util.List;
import java.util.UUID;


public interface DonorTypeService {

    DonorType getById(UUID id);

    List<DonorType> getAll();

    void save(DonorType donorType);

    void delete(DonorType donorType);

    DonorType findByName(String name);

}
