package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.BoatEntity;
import bg.softuni.boats.model.entity.PictureEntity;
import bg.softuni.boats.repository.BoatRepository;
import bg.softuni.boats.service.BoatService;
import bg.softuni.boats.service.CloudImageService;
import bg.softuni.boats.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BoatServiceImpl implements BoatService {

    private final CloudImageService cloudImageService;
    private final ModelMapper modelMapper;
    private final BoatRepository boatRepository;
    private final PictureService photoService;


    public BoatServiceImpl(CloudImageService cloudImageService, ModelMapper modelMapper, BoatRepository boatRepository, PictureService photoService) {
        this.cloudImageService = cloudImageService;
        this.modelMapper = modelMapper;
        this.boatRepository = boatRepository;
        this.photoService = photoService;
    }


    @Override
    public void addNewBoat(BoatDTO boatDTO) {
        MultipartFile image = boatDTO.getPhoto();
        BoatEntity boatEntity = modelMapper.map(boatDTO, BoatEntity.class);
        PictureEntity pictureEntity = new PictureEntity();

        if (image != null) {
            String imageUrl = cloudImageService.uploadImage(image);
            pictureEntity = photoService.saveImage(imageUrl);
        }

        boatEntity.setPhotos(Set.of(pictureEntity));
        boatRepository.save(boatEntity);

    }

    @Override
    public List<String> getAllBoatNames() {
        return boatRepository.findAll()
                .stream()
                .map(BoatEntity::getName)
                .toList();
    }

    @Override
    public BoatEntity getBoatEntityByName(String boatName) {
        Optional<BoatEntity> boatEntity = boatRepository.findByName(boatName);
        return boatEntity.orElse(null);
    }

    @Override
    public void saveToExistingBoat(UploadPictureDTO uploadPictureDTO) {
        BoatEntity boatEntity = getBoatEntityByName(uploadPictureDTO.getBoatName());
        String imageUrl = cloudImageService.uploadImage(uploadPictureDTO.getPicture());
        PictureEntity pictureEntity = photoService.saveImageWithTitle(imageUrl, uploadPictureDTO.getTitle());
        boatEntity.getPhotos().add(pictureEntity);
        boatRepository.save(boatEntity);
    }
}
