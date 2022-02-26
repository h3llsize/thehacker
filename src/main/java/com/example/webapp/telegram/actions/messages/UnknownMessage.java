package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownMessage extends MessageImpl {
    public UnknownMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        SendMessage sendMessage = generateMessage(action,"<b> Такой команды не существует, напиши <em>/start</em>, чтобы запустить бота </b> \n" +
                "\n" +
                "\uD83D\uDC3D Если это клавиатура, то её можно обновить, также написав /start");
        sendMessage.enableHtml(true);
        new RunMessageHandler(sendMessage);
    }
}
