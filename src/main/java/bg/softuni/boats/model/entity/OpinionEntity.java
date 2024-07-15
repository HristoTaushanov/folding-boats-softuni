package bg.softuni.boats.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "opinions")
public class OpinionEntity extends BaseEntity{

    @ManyToOne(optional = false)
    private UserEntity author;
    @Column(columnDefinition = "LONGTEXT")
    private String textContent;
    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BoatEntity boat;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "opinionEntity")
    private Set<CommentEntity> comments;

    public OpinionEntity() {
        this.comments = new HashSet<>();
    }



    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getTextContent() {
        return textContent;
    }

    public OpinionEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

