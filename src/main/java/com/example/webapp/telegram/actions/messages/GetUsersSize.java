package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.atomic.AtomicInteger;

public class GetUsersSize extends MessageImpl {
    public GetUsersSize(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        AtomicInteger userSize = new AtomicInteger();
        UserService.getAllUsers().forEach(z -> {
            userSize.getAndIncrement();
        });

        SendMessage sendMessage = generateMessage(action, userSize.get() + "");
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
