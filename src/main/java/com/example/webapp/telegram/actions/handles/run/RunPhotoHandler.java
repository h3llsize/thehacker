package com.example.webapp.telegram.actions.handles.run;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.impl.ErrorHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RunPhotoHandler extends Thread implements ErrorHandler {
    private SendPhoto sendMessage;

    public RunPhotoHandler(SendPhoto sendMessage)
    {
        this.sendMessage = sendMessage;
        start();
    }

    @Override
    public void run() {
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            onError(e.toString());
        }
    }

    @Override
    public void onError(String errorMessage) {
        Logger.sendConsole(errorMessage);
    }
}
