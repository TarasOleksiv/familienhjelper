package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.DonorType;

import java.util.UUID;

public interface DonorTypeDao extends JpaRepository<DonorType, UUID> {
    DonorType findByName(String name);
}
