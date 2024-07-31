package bg.softuni.boats.service.impl;

import bg.softuni.boats.service.CloudImageService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
public class CloudImageServiceImpl implements CloudImageService {

    private final Cloudinary cloudinary;


    public CloudImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        File image;

        try {
            image = File.createTempFile("temp_image", multipartFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            multipartFile.transferTo(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return this.cloudinary
                    .uploader()
                    .upload(image, Collections.emptyMap())
                    .get("url")
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
