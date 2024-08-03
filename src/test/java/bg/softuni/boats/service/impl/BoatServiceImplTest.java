package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.*;
import bg.softuni.boats.model.view.BoatDetailsViewModel;
import bg.softuni.boats.model.view.BoatViewModel;
import bg.softuni.boats.repository.BoatRepository;
import bg.softuni.boats.service.CloudImageService;
import bg.softuni.boats.service.PictureService;
import bg.softuni.boats.service.UserService;
import bg.softuni.boats.service.exception.BoatNotFoundException;
import bg.softuni.boats.service.impl.BoatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BoatServiceImplTest {

    @Mock
    private CloudImageService cloudImageService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BoatRepository boatRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BoatServiceImpl boatService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewBoat() {
        BoatDTO boatDTO = mock(BoatDTO.class);
        MultipartFile image = mock(MultipartFile.class);
        when(boatDTO.getPhoto()).thenReturn(image);
        when(cloudImageService.uploadImage(image)).thenReturn("imageUrl");
        when(pictureService.saveImage("imageUrl")).thenReturn(new PictureEntity());

        BoatEntity boatEntity = new BoatEntity();
        when(modelMapper.map(boatDTO, BoatEntity.class)).thenReturn(boatEntity);

        boatService.addNewBoat(boatDTO);

        verify(boatRepository, times(1)).save(boatEntity);
    }

    @Test
    public void testGetAllBoatNames() {
        BoatEntity boat1 = new BoatEntity();
        boat1.setName("Boat 1");
        BoatEntity boat2 = new BoatEntity();
        boat2.setName("Boat 2");

        when(boatRepository.findAll()).thenReturn(List.of(boat1, boat2));

        List<String> boatNames = boatService.getAllBoatNames();

        assertEquals(List.of("Boat 1", "Boat 2"), boatNames);
    }

    @Test
    public void testGetBoatEntityByName() {
        BoatEntity boatEntity = new BoatEntity();
        when(boatRepository.findByName("Boat 1")).thenReturn(Optional.of(boatEntity));

        BoatEntity foundBoat = boatService.getBoatEntityByName("Boat 1");

        assertEquals(boatEntity, foundBoat);
    }

    @Test
    public void testSaveToExistingBoat() {
        UploadPictureDTO uploadPictureDTO = mock(UploadPictureDTO.class);
        when(uploadPictureDTO.getBoatName()).thenReturn("Boat 1");
        when(uploadPictureDTO.getPicture()).thenReturn(mock(MultipartFile.class));
        when(uploadPictureDTO.getTitle()).thenReturn("Title");

        BoatEntity boatEntity = new BoatEntity();
        when(boatRepository.findByName("Boat 1")).thenReturn(Optional.of(boatEntity));
        when(cloudImageService.uploadImage(any())).thenReturn("imageUrl");
        when(pictureService.saveImageWithTitle("imageUrl", "Title")).thenReturn(new PictureEntity());

        boatService.saveToExistingBoat(uploadPictureDTO);

        verify(boatRepository, times(1)).save(boatEntity);
    }

    @Test
    public void testGetAllBoat() {
        BoatEntity boatEntity = new BoatEntity();
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setUrl("imageUrl");
        boatEntity.setPhotos(List.of(pictureEntity));

        when(boatRepository.findAll()).thenReturn(List.of(boatEntity));

        BoatViewModel boatViewModel = new BoatViewModel();
        when(modelMapper.map(boatEntity, BoatViewModel.class)).thenReturn(boatViewModel);

        List<BoatViewModel> boatList = boatService.getAllBoat();

        assertEquals(1, boatList.size());
        assertEquals("imageUrl", boatList.get(0).getPhoto());
    }

    @Test
    public void testGetBoatById() {
        BoatEntity boatEntity = new BoatEntity();
        when(boatRepository.findById(1L)).thenReturn(Optional.of(boatEntity));

        BoatDTO boatDTO = new BoatDTO();
        when(modelMapper.map(boatEntity, BoatDTO.class)).thenReturn(boatDTO);

        BoatDTO foundBoat = boatService.getBoatById(1L);

        assertEquals(boatDTO, foundBoat);
    }

    @Test
    public void testGetBoatByIdNotFound() {
        when(boatRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BoatNotFoundException.class, () -> boatService.getBoatById(1L));
    }

    @Test
    public void testGetBoatDetailsById() {
        BoatEntity boatEntity = new BoatEntity();
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setUrl("imageUrl");
        boatEntity.setPhotos(List.of(pictureEntity));

        when(boatRepository.findById(1L)).thenReturn(Optional.of(boatEntity));

        BoatDetailsViewModel boatDetailsViewModel = new BoatDetailsViewModel();
        when(modelMapper.map(boatEntity, BoatDetailsViewModel.class)).thenReturn(boatDetailsViewModel);

        BoatDetailsViewModel foundBoatDetails = boatService.getBoatDetailsById(1L);

        assertNotNull(foundBoatDetails);
    }

    @Test
    public void testAddNewOpinion() {
        BoatEntity boatEntity = new BoatEntity();
        UserEntity userEntity = new UserEntity();
        when(boatRepository.findById(1L)).thenReturn(Optional.of(boatEntity));
        when(userService.findByUsername("user")).thenReturn(Optional.of(userEntity));

        boatService.addNewOpinion("Great boat!", 1L, "user");

        assertEquals(1, boatEntity.getOpinions().size());
        assertEquals("Great boat!", boatEntity.getOpinions().get(0).getTextContent());
        assertEquals(userEntity, boatEntity.getOpinions().get(0).getAuthor());

        verify(boatRepository, times(1)).save(boatEntity);
    }

    @Test
    public void testAddNewOpinionComment() {
        BoatEntity boatEntity = new BoatEntity();
        OpinionEntity opinionEntity = new OpinionEntity();
        opinionEntity.setId(1L);
        boatEntity.setOpinions(List.of(opinionEntity));
        UserEntity userEntity = new UserEntity();
        when(boatRepository.findById(1L)).thenReturn(Optional.of(boatEntity));
        when(userService.findByUsername("user")).thenReturn(Optional.of(userEntity));

        boatService.addNewOpinionComment("Nice comment!", 1L, 1L, "user");

        assertEquals(1, opinionEntity.getComments().size());
        assertEquals("Nice comment!", opinionEntity.getComments().get(0).getContent());
        assertEquals(userEntity, opinionEntity.getComments().get(0).getAuthor());

        verify(boatRepository, times(1)).save(boatEntity);
    }
}

