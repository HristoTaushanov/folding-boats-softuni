package bg.softuni.boats.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

public class MessageEntity extends BaseEntity{

    @Column(nullable = false, name = "date_time")
    private Instant dateTime;

    @Column(name = "text_content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne(optional = false)
    private UserEntity recipient;

    public MessageEntity() {}

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public UserEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UserEntity recipient) {
        this.recipient = recipient;
    }
}
