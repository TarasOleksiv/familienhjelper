package ua.goit.java8.javadeveloper.service;

import ua.goit.java8.javadeveloper.model.Role;

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
