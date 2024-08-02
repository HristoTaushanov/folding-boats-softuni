package bg.softuni.boats.repository;

import bg.softuni.boats.model.entity.OpinionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionEntity, Long> {
}
