package ua.petros.service;

import ua.petros.model.Status;

import java.util.List;
import java.util.UUID;


public interface StatusService {

    Status getById(UUID id);

    List<Status> getAll();

    void save(Status status);

    void delete(Status status);

    Status findByName(String name);

}
