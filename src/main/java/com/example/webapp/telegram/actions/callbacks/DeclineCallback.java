package com.example.webapp.telegram.actions.callbacks;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DeclineCallback extends MessageImpl {
    public DeclineCallback(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        userEntity.setEvent(null);
        UserService.saveUser(userEntity);

        int messageId = action.getCallbackQuery().getMessage().getMessageId();
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(messageId);
        deleteMessage.setChatId(action.getCallbackQuery().getMessage().getChatId().toString());

        try {
            new Bot().execute(deleteMessage);
        } catch (TelegramApiException e) {
            Logger.sendConsole(e.getMessage());
        }
    }
}
