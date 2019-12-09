package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.RoleDao;
import ua.petros.model.Role;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 07/02/2018.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getById(UUID id) {
        return roleDao.getOne(id);
    }

    @Override
    public List<Role> getAll() { return roleDao.findAll(); }

    @Override
    public void create(Role role) {
        UUID uuid = UUID.randomUUID();
        role.setId(uuid);
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public void update(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
