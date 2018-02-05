package ua.goit.java8.javadeveloper.dao;

import ua.goit.java8.javadeveloper.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleDao extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
