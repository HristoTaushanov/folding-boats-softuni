package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.CommentDTOWithId;
import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private CommentEntity commentEntity;

    @BeforeEach
    void setUp() {
        commentEntity = new CommentEntity();
        commentEntity.setId(1L);
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(commentEntity));
        doNothing().when(commentRepository).deleteById(anyLong());

        commentService.deleteComment(1L);

        assertNull(commentEntity.getOpinion());
        verify(commentRepository).save(commentEntity);
        verify(commentRepository).deleteById(1L);
    }

    @Test
    void testDeleteComment_NotFound() {
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());

        commentService.deleteComment(1L);

        verify(commentRepository, never()).save(any(CommentEntity.class));
        verify(commentRepository).deleteById(1L);
    }

    @Test
    void testGetAllComments() {
        List<CommentEntity> commentEntities = new ArrayList<>();
        commentEntities.add(commentEntity);
        when(commentRepository.findAll()).thenReturn(commentEntities);

        List<CommentDTOWithId> allComments = commentService.getAllComments();

        assertNotNull(allComments);
        assertFalse(allComments.isEmpty());
        assertEquals(1, allComments.size());
        assertEquals(commentEntity.getId(), allComments.get(0).getId());

        verify(commentRepository).findAll();
    }
}

