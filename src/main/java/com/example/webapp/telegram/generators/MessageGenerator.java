package com.example.webapp.telegram.generators;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageGenerator {
    private final String message;
    private final Long Id;
    public Message executeMessage;

    public MessageGenerator(String message, Long Id) {
        this.message = message;
        this.Id = Id;
        exec();
    }

    public void exec() {
        message.replaceAll("\\.", "");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(Id.toString());
        sendMessage.enableHtml(true);
        sendMessage.enableMarkdownV2(true);

        new RunMessageHandler(sendMessage);
    }
}
