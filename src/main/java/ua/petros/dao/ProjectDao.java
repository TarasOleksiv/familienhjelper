package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Project;

import java.util.UUID;

public interface ProjectDao extends JpaRepository<Project, UUID> {
    Project findByName(String name);
}
