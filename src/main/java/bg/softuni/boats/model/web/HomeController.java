package bg.softuni.boats.model.web;

import java.security.Principal;
import java.util.List;

import bg.softuni.boats.model.dto.CommentDTOWithId;
import bg.softuni.boats.model.entity.CommentEntity;
import bg.softuni.boats.model.entity.PictureEntity;
import bg.softuni.boats.model.view.BoatViewModel;
import bg.softuni.boats.service.BoatService;
import bg.softuni.boats.service.CommentService;
import bg.softuni.boats.service.PictureService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    private final BoatService boatService;
    private final PictureService pictureService;
    private final CommentService commentService;

    public HomeController(BoatService boatService, PictureService pictureService, CommentService commentService) {
        this.boatService = boatService;
        this.pictureService = pictureService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal, @AuthenticationPrincipal UserDetails viewer) {

        List<BoatViewModel> allBoats = boatService.getAllBoat();
        model.addAttribute("boats", allBoats);

        return "home";
    }

    @GetMapping("/features")
    public String features() {
        return "features";
    }
}
