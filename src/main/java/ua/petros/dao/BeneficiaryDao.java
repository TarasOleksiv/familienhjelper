package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Beneficiary;

import java.util.UUID;

public interface BeneficiaryDao extends JpaRepository<Beneficiary, UUID> {
    Beneficiary findByName(String name);
}
