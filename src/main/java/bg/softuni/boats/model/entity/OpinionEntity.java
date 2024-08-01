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

    @OneToMany(targetEntity = CommentEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CommentEntity> comments;

    public OpinionEntity() {
        this.comments = new HashSet<>();
    }

    public OpinionEntity(UserEntity author, String textContent, LocalDateTime created, Set<CommentEntity> comments) {
        this.author = author;
        this.textContent = textContent;
        this.created = created;
        this.comments = comments;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public OpinionEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
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

    public OpinionEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public OpinionEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}

