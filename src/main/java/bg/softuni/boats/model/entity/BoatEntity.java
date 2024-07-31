package bg.softuni.boats.model.entity;

import bg.softuni.boats.model.enums.BoatTypeEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foldable_boats")
public class BoatEntity extends BaseEntity{
        @Column(nullable = false, unique = true)
        private String name;

        @Column(columnDefinition = "LONGTEXT")
        private String description;

        @Column(nullable = false)
        private Integer benchesCount;

        @Enumerated(EnumType.STRING)
        private BoatTypeEnum type;

        @OneToMany(fetch = FetchType.EAGER)
        private Set<PictureEntity> photos;

        @OneToMany(targetEntity = OpinionEntity.class, mappedBy = "boat")
        private Set<OpinionEntity> opinions;

        public BoatEntity() {
                this.photos = new HashSet<>();
                this.opinions = new HashSet<>();
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
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

        public Set<PictureEntity> getPhotos() {
                return photos;
        }

        public void setPhotos(Set<PictureEntity> photos) {
                this.photos = photos;
        }

        public Set<OpinionEntity> getOpinions() {
                return opinions;
        }

        public void setOpinions(Set<OpinionEntity> opinions) {
                this.opinions = opinions;
        }
}
