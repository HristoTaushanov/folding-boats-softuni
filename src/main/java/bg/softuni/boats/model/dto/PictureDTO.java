package bg.softuni.boats.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PictureDTO {

    private Long id;

    private String title;
    @NotNull(message = "URL cannot be empty")
    @Size(min = 3, max = 200, message = "URL must be between 3 and 200 characters long")
    private String url;

    public PictureDTO() {
    }

    public PictureDTO(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
