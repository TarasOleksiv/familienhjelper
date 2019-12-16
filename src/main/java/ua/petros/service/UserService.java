package ua.petros.service;

import ua.petros.model.User;

import java.util.List;
import java.util.UUID;



public interface UserService {

    User getById(UUID id);

    List<User> getAll();

    void save (User user);

    void delete(User user);

    User findByUsername(String username);

}
