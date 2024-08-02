package bg.softuni.boats.model.view;

import bg.softuni.boats.model.dto.OpinionDTO;
import bg.softuni.boats.model.enums.BoatTypeEnum;

import java.util.Set;

public class BoatViewModel {

    private Long id;
    private String name;
    private String description;
    private Integer benchesCount;
    private BoatTypeEnum type;
    private String photo;
    private Set<OpinionDTO> opinions;

    public BoatViewModel() {
    }

    public BoatViewModel(Long id, String name, String description, Integer benchesCount, BoatTypeEnum type, String photo, Set<OpinionDTO> opinions) {
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

    public BoatViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BoatViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BoatViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getBenchesCount() {
        return benchesCount;
    }

    public BoatViewModel setBenchesCount(Integer benchesCount) {
        this.benchesCount = benchesCount;
        return this;
    }

    public BoatTypeEnum getType() {
        return type;
    }

    public BoatViewModel setType(BoatTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public BoatViewModel setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Set<OpinionDTO> getOpinions() {
        return opinions;
    }

    public BoatViewModel setOpinions(Set<OpinionDTO> opinions) {
        this.opinions = opinions;
        return this;
    }
}
