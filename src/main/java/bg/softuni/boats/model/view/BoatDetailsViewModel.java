package bg.softuni.boats.model.view;

import bg.softuni.boats.model.dto.OpinionDTO;
import bg.softuni.boats.model.enums.BoatTypeEnum;

import java.util.Set;

public class BoatDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private Integer benchesCount;
    private BoatTypeEnum type;
    private Set<String> photo;
    private Set<OpinionDTO> opinions;

    public BoatDetailsViewModel() {
    }

    public BoatDetailsViewModel(Long id, String name, String description, Integer benchesCount, BoatTypeEnum type, Set<String> photo, Set<OpinionDTO> opinions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.benchesCount = benchesCount;
        this.type = type;
        this.photo = photo;
        this.opinions = opinions;
    }

    public Long getId() {
        return id;
    }

    public BoatDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BoatDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BoatDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getBenchesCount() {
        return benchesCount;
    }

    public BoatDetailsViewModel setBenchesCount(Integer benchesCount) {
        this.benchesCount = benchesCount;
        return this;
    }

    public BoatTypeEnum getType() {
        return type;
    }

    public BoatDetailsViewModel setType(BoatTypeEnum type) {
        this.type = type;
        return this;
    }

    public Set<String> getPhoto() {
        return photo;
    }

    public BoatDetailsViewModel setPhoto(Set<String> photo) {
        this.photo = photo;
        return this;
    }

    public Set<OpinionDTO> getOpinions() {
        return opinions;
    }

    public BoatDetailsViewModel setOpinions(Set<OpinionDTO> opinions) {
        this.opinions = opinions;
        return this;
    }
}
