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

}
