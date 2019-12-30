package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.ImageDao;
import ua.petros.model.Image;

import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public Image getById(UUID id) {
        return imageDao.getOne(id);
    }

    @Override
    public List<Image> getAll() {
        return imageDao.findAll();
    }

    @Override
    public void save(Image image) {
        imageDao.save(image);
    }

    @Override
    public void delete(Image image) {
        imageDao.delete(image);
    }

    @Override
    public List<Image> findByProjectId(UUID projectId) {
        return imageDao.findByProjectId(projectId);
    }
}
