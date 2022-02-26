package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.actions.keyboards.list.SubscribeToChannelKeyboard;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SubscribeToChannelMessage {
    public void exec(UserEntity userEntity) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" +
                "\uD83D\uDDD2 <b> Чтобы пользоваться ботом - нужно подписаться на наши каналы</b>\n" +
                "\uD83D\uDDD2 <b> Также советуем подписаться на нашего спонсора </b>\n" +
                "➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        sendMessage.setChatId(userEntity.getId().toString());
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(new SubscribeToChannelKeyboard());
        new RunMessageHandler(sendMessage);
    }
}
