package com.example.webapp.telegram.impl;

import com.example.webapp.telegram.actions.ActionStorage;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AdminCommandImpl {
    public final String name;

    public AdminCommandImpl(String name) {
        this.name = name;
        initialize();
    }

    public void exec(Update action, UserEntity userEntity) {
        SendMessage sendMessage = generateMessage(action, "Стандартное сообщение :/");
        new RunMessageHandler(sendMessage);

    }

    public void initialize()
    {
        ActionStorage.addAdminCommand(this, this.name);
    }

    public SendMessage generateMessage(Update action, String message)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(action.getMessage().getChatId().toString());
        sendMessage.enableHtml(true);

        return sendMessage;
    }

    @Override
    public String toString() {
        return "Action : " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MessageImpl){
            if (name.equals(((MessageImpl) obj).name)){
                return true;
            }
        }
        return false;
    }
}
