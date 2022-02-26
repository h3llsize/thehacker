package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.query.referral.CheckReferralsQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CheckReferalsMessage extends MessageImpl {
    public CheckReferalsMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        new CheckReferralsQuery(userEntity);
        generateMessage(action,"hello!");
    }
}
