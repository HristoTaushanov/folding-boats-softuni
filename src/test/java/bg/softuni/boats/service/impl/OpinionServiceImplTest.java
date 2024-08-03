package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.model.entity.OpinionEntity;
import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.repository.OpinionRepository;
import bg.softuni.boats.service.UserService;
import bg.softuni.boats.service.impl.OpinionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OpinionServiceImplTest {

    @Mock
    private OpinionRepository opinionRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OpinionServiceImpl opinionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddComment() {
        OpinionEntity opinionWithNoComments = new OpinionEntity();
        opinionWithNoComments.setComments(new ArrayList<>());

        OpinionEntity opinionWithComments = new OpinionEntity();
        CommentEntity existingComment = new CommentEntity();
        opinionWithComments.setComments(new ArrayList<>(List.of(existingComment)));

        List<OpinionEntity> opinionEntities = List.of(opinionWithNoComments, opinionWithComments);

        when(opinionRepository.findAll()).thenReturn(opinionEntities);

        UserEntity userEntity = new UserEntity();
        when(userService.getUserEntityById(7L)).thenReturn(userEntity);

        opinionService.addComment();

        assertEquals(1, opinionWithNoComments.getComments().size());
        assertEquals("Please add your comment here!", opinionWithNoComments.getComments().get(0).getContent());
        assertEquals(userEntity, opinionWithNoComments.getComments().get(0).getAuthor());

        verify(opinionRepository, times(1)).save(opinionWithNoComments);
        verify(opinionRepository, times(0)).save(opinionWithComments);
    }
}


