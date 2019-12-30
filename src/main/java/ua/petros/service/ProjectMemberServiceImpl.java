package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.ProjectDao;
import ua.petros.dao.ProjectMemberDao;
import ua.petros.model.Project;
import ua.petros.model.ProjectMember;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 29.12.2019.
 */

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Override
    public ProjectMember getById(UUID id) {
        return projectMemberDao.getOne(id);
    }

    @Override
    public List<ProjectMember> getAll() {
        return projectMemberDao.findAll();
    }

    @Override
    public void save(ProjectMember projectMember) {
        projectMemberDao.save(projectMember);
    }

    @Override
    public void delete(ProjectMember projectMember) {
        projectMemberDao.delete(projectMember);
    }

    @Override
    public List<ProjectMember> findByProjectId(UUID projectId) {
        return projectMemberDao.findByProjectId(projectId);
    }

}
