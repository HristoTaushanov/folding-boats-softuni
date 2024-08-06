package bg.softuni.boats.web;

import bg.softuni.boats.model.dto.OpinionDTO;
import bg.softuni.boats.service.BoatService;
import bg.softuni.boats.service.OpinionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/opinions")
public class OpinionController {

    @GetMapping("/all-for-boat/{id}")
    public String opinionsPerBoat(Model model, @PathVariable Long id){

        if(!model.containsAttribute("opinionDTO")){
            model.addAttribute("opinionDTO", new OpinionDTO());
        }

        return null;
    }

}
