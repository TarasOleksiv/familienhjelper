package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.StatusDao;
import ua.petros.model.Status;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 18.12.2019.
 */

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDao statusDao;

    @Override
    public Status getById(UUID id) {
        return statusDao.getOne(id);
    }

    @Override
    public List<Status> getAll() {
        return statusDao.findAll();
    }

    @Override
    public void save(Status status) {
        statusDao.save(status);
    }

    @Override
    public void delete(Status status) {
        statusDao.delete(status);
    }

    @Override
    public Status findByName(String name) {
        return statusDao.findByName(name);
    }
}
