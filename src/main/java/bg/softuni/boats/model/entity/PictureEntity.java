package bg.softuni.boats.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity{
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;
    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BoatEntity boat;

    public PictureEntity() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public BoatEntity getBoat() {
        return boat;
    }

    public void setBoat(BoatEntity boat) {
        this.boat = boat;
    }
}
