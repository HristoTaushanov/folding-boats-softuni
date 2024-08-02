package bg.softuni.boats.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class CommentForm {

    @NotEmpty(message = "Opinion cannot be empty")
    private String commentContent;


    public CommentForm() {
    }

    public CommentForm(String message) {
        this.commentContent = message;
    }

    public @NotEmpty(message = "Opinion cannot be empty") String getCommentContent() {
        return commentContent;
    }

    public CommentForm setCommentContent(@NotEmpty(message = "Opinion cannot be empty") String commentContent) {
        this.commentContent = commentContent;
        return this;
    }
}
