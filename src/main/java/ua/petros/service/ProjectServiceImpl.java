package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.ProjectDao;
import ua.petros.model.Project;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project getById(UUID id) {
        return projectDao.getOne(id);
    }

    @Override
    public List<Project> getAll() {
        return projectDao.findAll();
    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public void delete(Project project) {
        projectDao.delete(project);
    }

    @Override
    public Project findByName(String name) {
        return projectDao.findByName(name);
    }
}
