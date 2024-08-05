package bg.softuni.boats.model.dto;

public class CommentDTOWithId {
    private Long id;

    public CommentDTOWithId() {
    }

    public CommentDTOWithId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public CommentDTOWithId setId(Long id) {
        this.id = id;
        return this;
    }
}
