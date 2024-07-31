package bg.softuni.boats.service;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.model.dto.UploadPictureDTO;
import bg.softuni.boats.model.entity.BoatEntity;

import java.util.List;
import java.util.Set;

public interface BoatService {

    void addNewBoat(BoatDTO boatDTO);

    List<String> getAllBoatNames();

    BoatEntity getBoatEntityByName(String boatName);

    void saveToExistingBoat(UploadPictureDTO uploadPictureDTO);
}
