package bg.softuni.boats.model.dto;

import bg.softuni.boats.model.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.Set;

public class OpinionDTO {

    private Long id;

    private String author;

    private String textContent;

    private LocalDateTime created;

    private BoatDTO boat;

    private Set<CommentDTO> comments;

    public OpinionDTO() {
    }

    public OpinionDTO(Long id, String author, String textContent, LocalDateTime created, BoatDTO boat, Set<CommentDTO> comments) {
        this.id = id;
        this.author = author;
        this.textContent = textContent;
        this.created = created;
        this.boat = boat;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BoatDTO getBoat() {
        return boat;
    }

    public void setBoat(BoatDTO boat) {
        this.boat = boat;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }
}
