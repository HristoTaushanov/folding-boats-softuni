package bg.softuni.boats.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column(nullable = false)
    private Boolean approved;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OpinionEntity opinionEntity;

    public CommentEntity() {}

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public OpinionEntity getOpinionEntity() {
        return opinionEntity;
    }

    public void setOpinionEntity(OpinionEntity opinionEntity) {
        this.opinionEntity = opinionEntity;
    }
}

