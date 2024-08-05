package bg.softuni.boats.model.web;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.service.BoatService;
import bg.softuni.boats.service.PictureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;
    private final BoatService boatService;

    public PictureController(PictureService pictureService, BoatService boatService) {
        this.pictureService = pictureService;
        this.boatService = boatService;
    }


    @GetMapping("/upload")
    public String addBoat(Model model) {
        if(!model.containsAttribute("uploadPictureDTO")){
            model.addAttribute("uploadPictureDTO", new UploadPictureDTO());
        }

        List<String> boatNames = boatService.getAllBoatNames();
        model.addAttribute("boatNames", boatNames);

        return "picture-add";
    }

    @PostMapping("/upload")
    public String addBuilding(@Valid UploadPictureDTO uploadPictureDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("uploadPictureDTO", uploadPictureDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.uploadPictureDTO", bindingResult);
            return "redirect:upload";
        }

        boatService.saveToExistingBoat(uploadPictureDTO);

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePicture(@PathVariable("id") Long id){
        pictureService.deletePicture(id);
        return "redirect:/home";
    }






}
