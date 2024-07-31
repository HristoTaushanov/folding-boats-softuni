package bg.softuni.boats.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudImageService {

    String uploadImage(MultipartFile multipartFile);

}
