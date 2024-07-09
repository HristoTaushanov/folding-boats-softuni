package bg.softuni.emptyproject.model.entity;

import bg.softuni.emptyproject.model.enums.BoatTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class BoatEntity {
        BoatTypeEnum type;
        String name;
}
