package bg.softuni.boats.model.entity;

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

