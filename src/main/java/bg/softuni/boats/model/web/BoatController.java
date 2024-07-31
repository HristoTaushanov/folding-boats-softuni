package bg.softuni.boats.model.web;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.service.BoatService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boat")
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping("/add")
    public String addBoat(Model model) {
        if(!model.containsAttribute("boatDTO")){
            model.addAttribute("boatDTO", new BoatDTO());
        }
        return "boat-add";
    }

    @PostMapping("/add")
    public String addBuilding(@Valid BoatDTO boatDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("boatDTO", boatDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.boatDTO", bindingResult);
            return "redirect:add";
        }

        boatService.addNewBoat(boatDTO);

        return "redirect:/home";
    }

}
