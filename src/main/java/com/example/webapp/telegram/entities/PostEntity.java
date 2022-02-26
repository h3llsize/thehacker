package com.example.webapp.telegram.entities;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "post", schema = "public")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String Id;

    @Column(nullable = false)
    private String channelId;

    @Column(nullable = false)
    private String message;

    private String button;

    private String buttonUrl;

    private String buttonEvent;

    private InputFile photo;

    private LocalDate date;

    private boolean now;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    public String getButtonEvent() {
        return buttonEvent;
    }

    public void setButtonEvent(String enjoyEvent) {
        this.buttonEvent = enjoyEvent;
    }

    public InputFile getPhoto() {
        return photo;
    }

    public void setPhoto(InputFile photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isNow() {
        return now;
    }

    public void setNow(boolean now) {
        this.now = now;
    }
}
