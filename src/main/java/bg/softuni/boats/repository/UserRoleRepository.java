package bg.softuni.boats.repository;

import bg.softuni.boats.model.entity.UserRoleEntity;
import bg.softuni.boats.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);
}
