package com.example.webapp.telegram.actions.handles.run;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.impl.ErrorHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RunStickerHandler extends Thread implements ErrorHandler {
    private SendSticker sendMessage;

    public RunStickerHandler(SendSticker sendMessage)
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
