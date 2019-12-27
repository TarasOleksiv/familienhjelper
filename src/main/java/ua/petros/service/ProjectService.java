package ua.petros.service;

import ua.petros.model.Project;

import java.util.List;
import java.util.UUID;


public interface ProjectService {

    Project getById(UUID id);

    List<Project> getAll();

    void create(Project project);

    void delete(Project project);

    Project findByName(String name);

}
