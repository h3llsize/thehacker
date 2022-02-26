package com.example.webapp.telegram.actions.events;

import com.example.webapp.telegram.bomber.services.Telegram;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.gather.GatherHandler;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.impl.EventImpl;
import com.example.webapp.telegram.service.UserService;
import com.example.webapp.utils.TelegramUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartGatherEvent extends EventImpl {

    public StartGatherEvent(String name) {
        super(name);
    }

    @Override
    public void exec(Update update, UserEntity user, String params) {
        String number = update.getMessage().getText();

        String info = GatherHandler.getNumberInfo(number);

        new MessageGenerator(TelegramUtils.fixMessage(info), user.getId());

        user.setEvent(null);
        UserService.saveUser(user);
    }
}
