package bg.softuni.boats.service;

import bg.softuni.boats.model.dto.PictureDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.PictureEntity;

import java.security.Principal;
import java.util.List;

public interface PictureService {

    PictureEntity saveImage(String imageUrl);

    void saveToExistingBoat(UploadPictureDTO uploadPictureDTO);

    PictureEntity saveImageWithTitle(String imageUrl, String title);

    void deletePicture(Long id);

    List<PictureEntity> getAllPictures();

}
