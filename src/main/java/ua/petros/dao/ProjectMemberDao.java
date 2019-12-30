package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.ProjectMember;

import java.util.List;
import java.util.UUID;

public interface ProjectMemberDao extends JpaRepository<ProjectMember, UUID> {

    List<ProjectMember> findByProjectId(UUID projectId);
}
