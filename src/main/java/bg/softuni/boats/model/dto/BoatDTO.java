package bg.softuni.boats.model.dto;

import bg.softuni.boats.model.enums.BoatTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class BoatDTO {
    private String name;

    private Long id;

    private String description;

    private Integer benchesCount;

    private BoatTypeEnum type;

    private MultipartFile photo;

    private Set<OpinionDTO> opinions;

    public BoatDTO() {
    }

    public BoatDTO(String name, Long id, String description, Integer benchesCount, BoatTypeEnum type, MultipartFile photos, Set<OpinionDTO> opinions) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.benchesCount = benchesCount;
        this.type = type;
        this.photo = photos;
        this.opinions = opinions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBenchesCount() {
        return benchesCount;
    }

    public void setBenchesCount(Integer benchesCount) {
        this.benchesCount = benchesCount;
    }

    public BoatTypeEnum getType() {
        return type;
    }

    public void setType(BoatTypeEnum type) {
        this.type = type;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public Set<OpinionDTO> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<OpinionDTO> opinions) {
        this.opinions = opinions;
    }
}
