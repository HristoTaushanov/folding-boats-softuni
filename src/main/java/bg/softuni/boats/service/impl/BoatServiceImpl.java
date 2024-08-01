package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.CommentDTO;
import bg.softuni.boats.model.dto.OpinionDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.*;
import bg.softuni.boats.model.view.BoatDetailsViewModel;
import bg.softuni.boats.model.view.BoatViewModel;
import bg.softuni.boats.model.view.UserViewModel;
import bg.softuni.boats.repository.BoatRepository;
import bg.softuni.boats.service.BoatService;
import bg.softuni.boats.service.CloudImageService;
import bg.softuni.boats.service.PictureService;
import bg.softuni.boats.service.UserService;
import bg.softuni.boats.service.exception.BoatNotFoundException;
import bg.softuni.boats.service.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BoatServiceImpl implements BoatService {

    private final CloudImageService cloudImageService;
    private final ModelMapper modelMapper;
    private final BoatRepository boatRepository;
    private final PictureService photoService;
    private final UserService userService;


    public BoatServiceImpl(CloudImageService cloudImageService, ModelMapper modelMapper, BoatRepository boatRepository, PictureService photoService, UserService userService) {
        this.cloudImageService = cloudImageService;
        this.modelMapper = modelMapper;
        this.boatRepository = boatRepository;
        this.photoService = photoService;
        this.userService = userService;
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

        boatEntity.setPhotos(List.of(pictureEntity));
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

    @Override
    public List<BoatViewModel> getAllBoat() {
        return boatRepository.findAll()
                .stream()
                .map(b -> {
                    BoatViewModel boatViewModel = modelMapper.map(b, BoatViewModel.class);
                    String imageUrl = b.getPhotos().stream().findFirst().map(PictureEntity::getUrl).orElse(null);
                    boatViewModel.setPhoto(imageUrl);
                    return boatViewModel;
                })
                .toList();
    }

    @Override
    public BoatDTO getBoatById(Long id) {
        return boatRepository.findById(id)
                .map(b -> modelMapper.map(b, BoatDTO.class))
                .orElseThrow(() -> new BoatNotFoundException("Boat with id " + id + " not found!"));
    }

    @Override
    public BoatDetailsViewModel getBoatDetailsById(Long id) {
        return boatRepository.findById(id)
                .map(b -> {
                    BoatDetailsViewModel boatDetailsViewModel = modelMapper.map(b, BoatDetailsViewModel.class);

                    Set<String> photos = b.getPhotos()
                            .stream()
                            .map(PictureEntity::getUrl)
                            .collect(Collectors.toSet());
                    boatDetailsViewModel.setPhoto(photos);

                    Set<OpinionDTO> opinions = b.getOpinions()
                            .stream()
                            .map(o -> {
                                OpinionDTO opinionDTO = modelMapper.map(o, OpinionDTO.class);
                                opinionDTO.setAuthor(o.getAuthor().getUsername());

                                Set<CommentDTO> comments = o.getComments()
                                        .stream()
                                        .map(c -> {
                                            CommentDTO commentDTO = modelMapper.map(c, CommentDTO.class);
                                            commentDTO.setAuthor(c.getAuthor().getUsername());
                                            return commentDTO;
                                        })
                                        .collect(Collectors.toSet());

                                opinionDTO.setComments(comments);
                                return opinionDTO;
                            })
                            .collect(Collectors.toSet());
                    boatDetailsViewModel.setOpinions(opinions);

                    return boatDetailsViewModel;
                })
                .orElse(null);
    }

    @Override
    public void addNewOpinion(String opinion, Long id, String username) {
        BoatEntity boatEntity = boatRepository.findById(id).orElse(null);

        OpinionEntity opinionEntity = new OpinionEntity();
        opinionEntity.setTextContent(opinion);

        UserEntity userEntity = userService.findByUsername(username).orElse(null);

        opinionEntity.setAuthor(userEntity);
        opinionEntity.setCreated(LocalDateTime.now());

        boatEntity.getOpinions().add(opinionEntity);

        boatRepository.save(boatEntity);
    }

    @Override
    public void addNewOpinionComment(String commentContent, Long id, Long opinionId, String username) {
        BoatEntity boatEntity = boatRepository.findById(id).orElse(null);

        List<OpinionEntity> opinionEntity = boatEntity.getOpinions();
        for (OpinionEntity o : opinionEntity) {
            if (o.getId().equals(opinionId)) {

                UserEntity userEntity = userService.findByUsername(username).orElse(null);

                CommentEntity commentEntity = new CommentEntity();
                commentEntity.setContent(commentContent);
                commentEntity.setCreated(LocalDateTime.now());
                commentEntity.setAuthor(userEntity);

                o.getComments().add(commentEntity);
            }
        }
        boatRepository.save(boatEntity);
    }
}
