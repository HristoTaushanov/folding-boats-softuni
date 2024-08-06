package bg.softuni.boats.web;

import bg.softuni.boats.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommentController {
    private final CommentService commentsService;

    public CommentController(CommentService commentsService) {
        this.commentsService = commentsService;
    }

    @DeleteMapping("/comment/delete/{id}/{boatId}")
    public String deleteComment(@PathVariable("id") Long id,
                                @PathVariable("boatId") Long boatId){
        commentsService.deleteComment(id);
        return "redirect:/boat/detail/{boatId}";
    }
}
