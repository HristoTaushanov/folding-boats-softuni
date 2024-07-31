package bg.softuni.boats.repository;

import bg.softuni.boats.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PictureEntity, Long> {
}
