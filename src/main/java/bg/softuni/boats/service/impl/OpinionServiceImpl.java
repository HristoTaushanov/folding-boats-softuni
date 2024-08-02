package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.model.entity.OpinionEntity;
import bg.softuni.boats.repository.OpinionRepository;
import bg.softuni.boats.service.OpinionService;
import bg.softuni.boats.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final UserService userService;

    public OpinionServiceImpl(OpinionRepository opinionRepository, UserService userService) {
        this.opinionRepository = opinionRepository;
        this.userService = userService;
    }

    @Override
    public void addComment() {
        List<OpinionEntity> opinionEntities = opinionRepository.findAll();
        for (OpinionEntity opinionEntity : opinionEntities) {
            if (opinionEntity.getComments().isEmpty()){
                CommentEntity commentEntity = new CommentEntity();
                commentEntity.setAuthor(userService.getUserEntityById(7L));
                commentEntity.setCreated(LocalDateTime.now());
                commentEntity.setContent("Please add your comment here!");
                opinionEntity.getComments().add(commentEntity);
                opinionRepository.save(opinionEntity);
            }
        }
    }
}
