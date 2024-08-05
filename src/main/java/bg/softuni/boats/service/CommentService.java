package bg.softuni.boats.service;

import bg.softuni.boats.model.dto.CommentDTOWithId;
import bg.softuni.boats.model.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    void deleteComment(Long id);

    List<CommentDTOWithId> getAllComments();
}
