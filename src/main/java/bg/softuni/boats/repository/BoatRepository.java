package bg.softuni.boats.repository;

import bg.softuni.boats.model.entity.BoatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoatRepository extends JpaRepository<BoatEntity, Long> {

    Optional<BoatEntity> findByName(String boatName);
}
