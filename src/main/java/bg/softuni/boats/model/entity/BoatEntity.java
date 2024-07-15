package bg.softuni.boats.model.entity;

import bg.softuni.boats.model.enums.BoatTypeEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class BoatEntity extends BaseEntity{
        @Column(nullable = false, unique = true)
        private String name;
        @Column(nullable = false)
        private Integer benchesCount;

        @Enumerated(EnumType.STRING)
        private BoatTypeEnum type;

        @OneToMany(targetEntity = OpinionEntity.class, mappedBy = "boat")
        private Set<OpinionEntity> opinions;

}
