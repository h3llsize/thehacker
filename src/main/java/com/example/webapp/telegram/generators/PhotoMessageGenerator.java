package com.example.webapp.telegram.generators;

import com.example.webapp.telegram.actions.handles.run.RunPhotoHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class PhotoMessageGenerator {
    private String channelID, message, button, buttonUrl;
    private InputFile photo;

    public PhotoMessageGenerator(String channelID, String message, InputFile photo, String button, String buttonUrl) {
        this.channelID = channelID;
        this.message = message;
        this.photo = photo;
        this.button = button;
        this.buttonUrl = buttonUrl;
    }

    public PhotoMessageGenerator(String channelID, String message, InputFile photo) {
        this.channelID = channelID;
        this.message = message;
        this.photo = photo;
    }

    public PhotoMessageGenerator() {

    }

    public PhotoMessageGenerator setChannelID(String channelID) {
        this.channelID = channelID;
        return this;
    }

    public PhotoMessageGenerator setMessage(String message) {
        this.message = message;
        return this;
    }

    public PhotoMessageGenerator setButton(String button) {
        this.button = button;
        return this;
    }

    public PhotoMessageGenerator setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
        return this;
    }

    public PhotoMessageGenerator setPhoto(InputFile photo) {
        this.photo = photo;
        return this;
    }
    public void execute() {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(photo);
        sendPhoto.setCaption(message);
        sendPhoto.setParseMode("MarkdownV2");
        sendPhoto.setChatId(channelID);
        //enableButton

        new RunPhotoHandler(sendPhoto);
    }
}
