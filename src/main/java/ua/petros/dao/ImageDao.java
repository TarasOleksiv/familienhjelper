package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Image;
import ua.petros.model.ProjectMember;

import java.util.List;
import java.util.UUID;

public interface ImageDao extends JpaRepository<Image, UUID> {

    List<Image> findByProjectId(UUID projectId);
}
