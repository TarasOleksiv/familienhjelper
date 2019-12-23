package ua.petros.service;

import ua.petros.dao.UserDao;
import ua.petros.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getById(UUID id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        if (user.getPassword() == null){
            user.setPassword(getById(user.getId()).getPassword());
        }
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
