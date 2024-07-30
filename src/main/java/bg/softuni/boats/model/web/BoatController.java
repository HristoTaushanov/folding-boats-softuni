package bg.softuni.boats.model.web;

import bg.softuni.boats.model.dto.BoatDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boat")
public class BoatController {
    @GetMapping("/add")
    public String addBoat(Model model) {
        if(!model.containsAttribute("boatDTO")){
            model.addAttribute("boatDTO", new BoatDTO());
        }
        return "boat-add";
    }
}
