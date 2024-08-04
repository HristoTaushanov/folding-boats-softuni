package bg.softuni.boats.model.web;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.CommentForm;
import bg.softuni.boats.model.dto.OpinionForm;
import bg.softuni.boats.model.view.BoatDetailsViewModel;
import bg.softuni.boats.service.BoatService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/boat")
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping("/add")
    public String addBoat(Model model) {
        if (!model.containsAttribute("boatDTO")) {
            model.addAttribute("boatDTO", new BoatDTO());
        }
        return "boat-add";
    }

    @PostMapping("/add")
    public String addBoat(@Valid BoatDTO boatDTO,
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

    @GetMapping("/detail/{id}")
    public String detailBoat(@PathVariable Long id, Model model) {
        BoatDetailsViewModel boat = boatService.getBoatDetailsById(id);
        model.addAttribute("boat", boat);
        model.addAttribute("opinionForm", new OpinionForm());
        model.addAttribute("commentForm", new CommentForm());
        return "boat-detail";
    }

    @PostMapping("/detail/add-opinion/{id}")
    public String detailBoat(@PathVariable Long id, @Valid OpinionForm opinionForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("opinionForm", opinionForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.opinionForm", bindingResult);
            return "redirect:/boat/detail/{id}";
        }

        boatService.addNewOpinion(opinionForm.getMessage(), id, principal.getName());

        return "redirect:/boat/detail/{id}";
    }

    @PostMapping("/detail/add-comment/{id}/{opinionId}")
    public String addComment(@PathVariable Long id,
                             @PathVariable Long opinionId,
                             @Valid CommentForm commentForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentForm", commentForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentForm", bindingResult);
            return "redirect:/boat/detail/{id}";
        }

        boatService.addNewOpinionComment(commentForm.getCommentContent(), id, opinionId, principal.getName());

        return "redirect:/boat/detail/{id}";
    }

}
