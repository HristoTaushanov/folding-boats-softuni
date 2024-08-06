package bg.softuni.boats.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public class OpinionDTO {

    private Long id;

    private String author;
    @NotNull(message = "Opinion cannot be empty")
    private String textContent;

    private LocalDateTime created;

    private Set<CommentDTO> comments;

    public OpinionDTO() {
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

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }
}
