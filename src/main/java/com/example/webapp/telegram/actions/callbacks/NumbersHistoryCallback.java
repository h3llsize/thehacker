package com.example.webapp.telegram.actions.callbacks;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NumbersHistoryCallback extends MessageImpl {
    public NumbersHistoryCallback(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        SendMessage sendMessage = new SendMessage();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("⚔️➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n").append("<b>Ваша история номеров</b> : \n");

        for(int i = 0; i < userEntity.getNumbersList().size(); i++)
        {
            stringBuilder.append("<code>").append(userEntity.getNumbersList().get(i)).append("</b>");

            if(i < userEntity.getNumbersList().size() - 1)
            {
                stringBuilder.append("\n");
            }
        }
        sendMessage.setText(stringBuilder.toString());
        sendMessage.enableHtml(true);
        sendMessage.setChatId(action.getCallbackQuery().getMessage().getChatId().toString());

        new RunMessageHandler(sendMessage);
    }
}
