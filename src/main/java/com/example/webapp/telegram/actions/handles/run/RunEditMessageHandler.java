package com.example.webapp.telegram.actions.handles.run;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.impl.ErrorHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RunEditMessageHandler extends Thread implements ErrorHandler {
    private final EditMessageText sendMessage;

    public RunEditMessageHandler(EditMessageText sendMessage)
    {
        this.sendMessage = sendMessage;
        start();
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void run() {
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            onError(e.toString());
        }
    }
}
