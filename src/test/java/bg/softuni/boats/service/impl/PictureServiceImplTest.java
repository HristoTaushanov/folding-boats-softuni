package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.PictureEntity;
import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.repository.PhotoRepository;
import bg.softuni.boats.service.CloudImageService;
import bg.softuni.boats.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureServiceImplTest {

    @Mock
    private CloudImageService cloudImageService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PictureServiceImpl pictureService;

    private UserEntity adminUser;
    private PictureEntity pictureEntity;

    @BeforeEach
    void setUp() {
        adminUser = new UserEntity();
        adminUser.setId(1L);
        adminUser.setUsername("admin");

        pictureEntity = new PictureEntity();
        pictureEntity.setId(1L);
        pictureEntity.setUrl("http://example.com/image.jpg");
        pictureEntity.setAuthor(adminUser);
        pictureEntity.setTitle("Default title");
    }

    @Test
    void testSaveImage() {
        when(userService.getAdminEntity()).thenReturn(adminUser);
        when(photoRepository.save(any(PictureEntity.class))).thenReturn(pictureEntity);

        PictureEntity savedPicture = pictureService.saveImage("http://example.com/image.jpg");

        assertNotNull(savedPicture);
        assertEquals("http://example.com/image.jpg", savedPicture.getUrl());
        assertEquals("Default title", savedPicture.getTitle());
        assertEquals(adminUser, savedPicture.getAuthor());

        verify(userService).getAdminEntity();
        verify(photoRepository).save(any(PictureEntity.class));
    }

    @Test
    void testSaveToExistingBoat() {
        UploadPictureDTO uploadPictureDTO = new UploadPictureDTO();
        MultipartFile multipartFile = mock(MultipartFile.class);
        uploadPictureDTO.setPicture(multipartFile);
        uploadPictureDTO.setBoatName("Test Boat");

        when(userService.getAdminEntity()).thenReturn(adminUser);
        when(cloudImageService.uploadImage(any(MultipartFile.class))).thenReturn("http://example.com/uploaded.jpg");
        when(modelMapper.map(any(UploadPictureDTO.class), eq(PictureEntity.class))).thenReturn(pictureEntity);
        when(photoRepository.save(any(PictureEntity.class))).thenReturn(pictureEntity);

        pictureService.saveToExistingBoat(uploadPictureDTO);

        verify(userService).getAdminEntity();
        verify(cloudImageService).uploadImage(any(MultipartFile.class));
        verify(modelMapper).map(uploadPictureDTO, PictureEntity.class);
        verify(photoRepository).save(any(PictureEntity.class));
    }

    @Test
    void testSaveImageWithTitle() {
        when(userService.getAdminEntity()).thenReturn(adminUser);
        when(photoRepository.save(any(PictureEntity.class))).thenReturn(pictureEntity);

        PictureEntity savedPicture = pictureService.saveImageWithTitle("http://example.com/image.jpg", "Custom Title");

        assertNotNull(savedPicture);
        assertEquals("http://example.com/image.jpg", savedPicture.getUrl());
        assertEquals("Default title", savedPicture.getTitle());
        assertEquals(adminUser, savedPicture.getAuthor());

        verify(userService).getAdminEntity();
        verify(photoRepository).save(any(PictureEntity.class));
    }

    @Test
    void testDeletePicture() {
        doNothing().when(photoRepository).deleteById(anyLong());

        pictureService.deletePicture(1L);

        verify(photoRepository).deleteById(1L);
    }

    @Test
    void testGetAllPictures() {
        when(photoRepository.findAll()).thenReturn(List.of(pictureEntity));

        List<PictureEntity> allPictures = pictureService.getAllPictures();

        assertNotNull(allPictures);
        assertFalse(allPictures.isEmpty());
        assertEquals(1, allPictures.size());
        assertEquals(pictureEntity, allPictures.get(0));

        verify(photoRepository).findAll();
    }
}

