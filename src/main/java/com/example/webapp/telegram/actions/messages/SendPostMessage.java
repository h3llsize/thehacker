package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendPostMessage extends MessageImpl {

    public SendPostMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        if(userEntity.isAdmin())
        {
            new RunMessageHandler(generateMessage(action,"Отправьте пост в следующем сообщении."));
            userEntity.setEvent("sendPost");
            UserService.saveUser(userEntity);
        }
    }
}
