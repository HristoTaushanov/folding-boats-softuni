package bg.softuni.boats.model.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;

    private Boolean approved;

    private LocalDateTime created;

    private String content;

    private String author;

    private OpinionDTO opinion;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Boolean approved, LocalDateTime created, String content, String author, OpinionDTO opinion) {
        this.id = id;
        this.approved = approved;
        this.created = created;
        this.content = content;
        this.author = author;
        this.opinion = opinion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public OpinionDTO getOpinion() {
        return opinion;
    }

    public void setOpinion(OpinionDTO opinion) {
        this.opinion = opinion;
    }
}
