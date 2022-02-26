package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class YesCommand extends MessageImpl {

    public YesCommand(String name) {
        super(name);
    }


    @Override
    public void exec(Update action, UserEntity userEntity) {
        new RunMessageHandler(generateMessage(action,"Пизда"));
    }
}
