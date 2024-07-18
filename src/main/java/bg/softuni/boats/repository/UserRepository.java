package bg.softuni.boats.repository;


import bg.softuni.boats.model.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional
    Optional<UserEntity> findByUsername(String username);

    Collection<Object> findByEmail(String value);

    Optional<UserEntity> findUserByEmail(String value);
}
