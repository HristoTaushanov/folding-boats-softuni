package bg.softuni.boats.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureDTO {

    private Long id;

    private String title;

    private MultipartFile picture;

    private String boatName;

    public UploadPictureDTO() {
    }

    public UploadPictureDTO(Long id, String title, MultipartFile picture, String boatName) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.boatName = boatName;
    }

    public Long getId() {
        return id;
    }

    public UploadPictureDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UploadPictureDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UploadPictureDTO setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getBoatName() {
        return boatName;
    }

    public UploadPictureDTO setBoatName(String boatName) {
        this.boatName = boatName;
        return this;
    }
}
