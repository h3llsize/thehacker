package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GatherStartMessage extends MessageImpl {
    public GatherStartMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        userEntity.setEvent("gather");
        UserService.saveUser(userEntity);

        new MessageGenerator("Введите номер телефона :", userEntity.getId());
    }
}
