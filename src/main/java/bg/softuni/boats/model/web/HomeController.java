package bg.softuni.boats.model.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
     public String home(Model model) {
        model.addAttribute("latest_posts", List.of("Post 1", "Post 2"));
        return "home";
    }
}
