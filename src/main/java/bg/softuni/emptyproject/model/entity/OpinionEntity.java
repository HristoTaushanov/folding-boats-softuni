package bg.softuni.emptyproject.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "opinions")
public class OpinionEntity extends BaseEntity{

    @ManyToOne
    private UserEntity author;
    @Column(nullable = false)
    private String textContent;
    @Column(nullable = false)
    private LocalDateTime created;

    public OpinionEntity() {

    }


    }

