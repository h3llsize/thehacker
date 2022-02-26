package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.actions.handles.run.RunStickerHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.parser.ParserStorage;
import com.example.webapp.telegram.service.UserService;
import com.example.webapp.telegram.stickers.StickersStorage;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class BomberAttackMessage extends MessageImpl {
    public BomberAttackMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        if(userEntity.getReferralsList().size() < 5 && !userEntity.isAdmin())
        {
            SendMessage sendMessage = generateMessage(action,"\uD83D\uDDD2 Чтобы пользоваться бомбером, вы должны иметь <b>5 активных рефералов</b>");
            new RunMessageHandler(sendMessage);
            return;
        }

        if (ParserStorage.isUpdating)
        {
            SendMessage sendMessage = generateMessage(action,"\uD83D\uDDD2 В данный момент мы обновляем базу прокси, это занимает не более 10 минут.");
            new RunMessageHandler(sendMessage);
            return;
        }

        SendMessage sendMessage = generateMessage(action, "➖➖➖➖\uD83D\uDD34➖➖➖➖\n" +
                "<b>Введите номер и количество повторов \n[от 10 до 50 через пробел]</b>\n\n " +
                "〰 пример : <i><b>[+7XXXXXXXXXX 15]</b></i>\n" +
                "➖➖➖➖\uD83D\uDD34➖➖➖➖\n");
        sendMessage.setReplyMarkup(new ButtonGenerator().generate("⚙️ Отменить","decline",true));
        sendMessage.enableHtml(true);

        new RunMessageHandler(sendMessage);

        userEntity.setEvent("startBomber");
        UserService.saveUser(userEntity);
    }
}
