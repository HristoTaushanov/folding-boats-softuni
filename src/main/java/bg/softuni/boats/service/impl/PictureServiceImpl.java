package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.PictureEntity;
import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.repository.PhotoRepository;
import bg.softuni.boats.service.CloudImageService;
import bg.softuni.boats.service.PictureService;
import bg.softuni.boats.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {
    private final CloudImageService cloudImageService;
    private final ModelMapper modelMapper;
    private final PhotoRepository photoRepository;
    private final UserService userService;

    public PictureServiceImpl(CloudImageService cloudImageService, ModelMapper modelMapper, PhotoRepository photoRepository, UserService userService) {
        this.cloudImageService = cloudImageService;
        this.modelMapper = modelMapper;
        this.photoRepository = photoRepository;
        this.userService = userService;
    }

    @Override
    public PictureEntity saveImage(String imageUrl) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setUrl(imageUrl);

        UserEntity userEntity = userService.getAdminEntity(); //htaushanov
        pictureEntity.setAuthor(userEntity)
                .setTitle("Default title");

        return photoRepository.save(pictureEntity);
    }

    @Override
    public void saveToExistingBoat(UploadPictureDTO uploadPictureDTO) {

        UserEntity author = userService.getAdminEntity(); //htaushanov

//        BoatEntity boatEntity = boatService.getBoatEntityByName(uploadPictureDTO.getBoatName()); //boatName

        String imageUrl = cloudImageService.uploadImage(uploadPictureDTO.getPicture()); //pictureURL

        PictureEntity pictureEntity = modelMapper.map(uploadPictureDTO, PictureEntity.class);
        pictureEntity
                .setAuthor(author)
                .setUrl(imageUrl);

        photoRepository.save(pictureEntity);
    }

    @Override
    public PictureEntity saveImageWithTitle(String imageUrl, String title) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setUrl(imageUrl);

        UserEntity userEntity = userService.getAdminEntity(); //htaushanov
        pictureEntity.setAuthor(userEntity)
                .setTitle(title);

        return photoRepository.save(pictureEntity);
    }
}
