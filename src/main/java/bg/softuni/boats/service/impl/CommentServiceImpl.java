package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.CommentDTOWithId;
import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.repository.CommentRepository;
import bg.softuni.boats.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentsRepository;

    public CommentServiceImpl(CommentRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        CommentEntity commentEntity = commentsRepository.findById(id).orElse(null);
        if (commentEntity!= null){
            commentEntity.setOpinion(null);
            commentsRepository.save(commentEntity);
        }
        commentsRepository.deleteById(id);
    }

    @Override
    public List<CommentDTOWithId> getAllComments() {
        List<CommentEntity> allComments = commentsRepository.findAll();
        List<CommentDTOWithId> commentDTOWithIdsList = new ArrayList<>();
        for (CommentEntity commentEntity : allComments) {
            CommentDTOWithId commentDTOWithId = new CommentDTOWithId();
            commentDTOWithId.setId(commentEntity.getId());
            commentDTOWithIdsList.add(commentDTOWithId);
        }
        return commentDTOWithIdsList;
    }
}
