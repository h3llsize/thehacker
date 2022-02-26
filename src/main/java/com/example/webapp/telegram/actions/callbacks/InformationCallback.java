package com.example.webapp.telegram.actions.callbacks;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class InformationCallback extends MessageImpl {
    public InformationCallback(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        SendMessage sendMessage = generateMessage(action, "\uD83D\uDEE0 ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" +
                "<b>1. Мы работаем только в ознакомительных целях, за злоупотребление сервиса ответственность не несём</b> \n" +
                "<b>2. Чтобы пользователь смог зарегистрироваться по вашей реферальной ссылке, перед этим он должен вступить в канал.</b>\n" +
                "\uD83D\uDEE0 ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        sendMessage.enableHtml(true);
        new RunMessageHandler(sendMessage);
    }
}
