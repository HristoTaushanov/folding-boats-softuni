package bg.softuni.boats.service;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.BoatEntity;
import bg.softuni.boats.model.view.BoatDetailsViewModel;
import bg.softuni.boats.model.view.BoatViewModel;

import java.util.List;
import java.util.Set;

public interface BoatService {

    void addNewBoat(BoatDTO boatDTO);

    List<String> getAllBoatNames();

    BoatEntity getBoatEntityByName(String boatName);

    void saveToExistingBoat(UploadPictureDTO uploadPictureDTO);

    List<BoatViewModel> getAllBoat();

    BoatDTO getBoatById(Long id);

    BoatDetailsViewModel getBoatDetailsById(Long id);

    void addNewOpinion(String opinion, Long id ,String username);

    void addNewOpinionComment(String commentContent, Long id,Long opinionId, String name);
}
