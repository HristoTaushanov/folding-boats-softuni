package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.repository.CommentRepository;
import bg.softuni.boats.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentsRepository;

    public CommentServiceImpl(CommentRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

}
