package com.example.webapp.telegram.actions.handles.run;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.impl.ErrorHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RunMessageHandler extends Thread implements ErrorHandler {
    private final SendMessage sendMessage;
    public Message message;

    public RunMessageHandler(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
        start();
    }

    @Override
    public void run() {
        try {
            this.message = new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            onError(e.toString());
        }
    }

    @Override
    public void onError(String errorMessage) {
        Logger.sendConsole(errorMessage);
    }
}
