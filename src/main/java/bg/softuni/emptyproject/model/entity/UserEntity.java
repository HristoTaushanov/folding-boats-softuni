package bg.softuni.emptyproject.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private List<UserRoleEntity> role;

    @OneToMany(mappedBy = "BoatTypeName")
    private List<OpinionEntity> addedOpinionEntities;

}
