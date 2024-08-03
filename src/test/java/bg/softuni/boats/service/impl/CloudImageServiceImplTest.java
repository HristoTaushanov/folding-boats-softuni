package bg.softuni.boats.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CloudImageServiceImplTest {

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private Uploader uploader;

    @InjectMocks
    private CloudImageServiceImpl cloudImageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(cloudinary.uploader()).thenReturn(uploader);
    }

    @Test
    public void testUploadImageSuccess() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("file", "test.png", "image/png", new byte[]{1, 2, 3, 4});

        File tempFile = File.createTempFile("temp_image", "test.png");
        multipartFile.transferTo(tempFile);

        when(uploader.upload(any(File.class), any(Map.class))).thenReturn(Collections.singletonMap("url", "http://example.com/test.png"));

        String url = cloudImageService.uploadImage(multipartFile);

        assertEquals("http://example.com/test.png", url);

    }

    @Test
    public void testCreateTempFileShouldThrow() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn(null);
        File image = File.createTempFile("temp_image", multipartFile.getOriginalFilename());

        assertThrows(RuntimeException.class, () -> cloudImageService.uploadImage(multipartFile));
    }

    @Test
    public void testUploadImageIOExceptionDuringFileCreation() {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("/");

        assertThrows(RuntimeException.class, () -> cloudImageService.uploadImage(multipartFile));
    }

    @Test
    public void testUploadImageIOExceptionDuringFileTransfer() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test.png");
        doThrow(new IOException()).when(multipartFile).transferTo(any(File.class));

        assertThrows(RuntimeException.class, () -> cloudImageService.uploadImage(multipartFile));
    }

    @Test
    public void testUploadImageIOExceptionDuringUpload() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("file", "test.png", "image/png", new byte[]{1, 2, 3, 4});

        File tempFile = File.createTempFile("temp_image", "test.png");
        multipartFile.transferTo(tempFile);

        when(uploader.upload(any(File.class), any(Map.class))).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> cloudImageService.uploadImage(multipartFile));

    }
}

