package ua.petros.dao;

import ua.petros.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleDao extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
