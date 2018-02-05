package ua.goit.java8.javadeveloper.dao;

import ua.goit.java8.javadeveloper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
