package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.query.BotSponsor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WorldOfScamMessage extends MessageImpl {
    public WorldOfScamMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        SendMessage sendMessage = generateMessage(action, BotSponsor.message);
        sendMessage.setReplyMarkup(new ButtonGenerator("Ссылка", BotSponsor.channelUrl, false).getInlineKeyboardMarkup());

        new RunMessageHandler(sendMessage);
    }
}
