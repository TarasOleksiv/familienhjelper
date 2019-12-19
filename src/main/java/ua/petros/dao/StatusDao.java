package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Status;

import java.util.UUID;

public interface StatusDao extends JpaRepository<Status, UUID> {
    Status findByName(String name);
}
