package ua.goit.java8.javadeveloper.service;

import ua.goit.java8.javadeveloper.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Service class for {@link User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    User getById(UUID id);

    List<User> getAll();

    void create(User user);

    void delete(User user);

    void update(User user);

    User findByUsername(String username);
}
