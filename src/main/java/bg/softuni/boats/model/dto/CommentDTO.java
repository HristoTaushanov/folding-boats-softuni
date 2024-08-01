package bg.softuni.boats.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;

    private LocalDateTime created;

    @NotNull(message = "Content cannot be empty")
    private String content;

    private String author;


    public CommentDTO() {
    }

    public CommentDTO(Long id, LocalDateTime created, String content, String author) {
        this.id = id;
        this.created = created;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "author='" + author + '\'' +
                '}';
    }
}
