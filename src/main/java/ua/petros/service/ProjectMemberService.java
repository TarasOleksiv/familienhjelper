package ua.petros.service;

import ua.petros.model.Project;
import ua.petros.model.ProjectMember;

import java.util.List;
import java.util.UUID;


public interface ProjectMemberService {

    ProjectMember getById(UUID id);

    List<ProjectMember> getAll();

    void save(ProjectMember projectMember);

    void delete(ProjectMember projectMember);

    List<ProjectMember> findByProjectId(UUID projectId);

}
