package bg.softuni.boats.service.schedulars;

import bg.softuni.boats.service.CommentService;
import bg.softuni.boats.service.OpinionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class AddCommentSchedular {
    private final CommentService commentsService;
    private final OpinionService opinionService;

    public AddCommentSchedular(CommentService commentsService, OpinionService opinionService) {
        this.commentsService = commentsService;
        this.opinionService = opinionService;
    }

    @Scheduled(fixedRate = 1000000)
    public void addComment() {
        opinionService.addComment();
    }

//    @Scheduled(fixedRate = 1000)
//    public void printOnTheConsole() {
//        System.out.println("Print on the console")
}
