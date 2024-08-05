package bg.softuni.boats.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    private OpinionEntity opinion;

    public CommentEntity() {
    }

    public CommentEntity(LocalDateTime created, String content, UserEntity author, OpinionEntity opinion) {
        this.created = created;
        this.content = content;
        this.author = author;
        this.opinion = opinion;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public OpinionEntity getOpinion() {
        return opinion;
    }

    public CommentEntity setOpinion(OpinionEntity opinion) {
        this.opinion = opinion;
        return this;
    }
}

