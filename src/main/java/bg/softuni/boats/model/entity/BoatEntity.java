package bg.softuni.boats.model.entity;

import bg.softuni.boats.model.enums.BoatTypeEnum;
import jakarta.persistence.*;

@Entity
@Table
public class BoatEntity extends BaseEntity{
        @Column(nullable = false, unique = true)
        private String name;
        @Column(nullable = false)
        private Integer benchesCount;

        @Enumerated(EnumType.STRING)
        private BoatTypeEnum type;

}
