package ua.petros.service;

import ua.petros.model.Role;

import java.util.List;
import java.util.UUID;


public interface RoleService {

    Role getById(UUID id);

    List<Role> getAll();

    void create(Role role);

    void delete(Role role);

    void update(Role role);

    Role findByName(String name);

}
