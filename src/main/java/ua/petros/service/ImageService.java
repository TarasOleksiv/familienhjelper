package ua.petros.service;

import ua.petros.model.Image;
import ua.petros.model.Status;

import java.util.List;
import java.util.UUID;


public interface ImageService {

    Image getById(UUID id);

    List<Image> getAll();

    void save(Image image);

    void delete(Image image);

    List<Image> findByProjectId(UUID projectId);
}
