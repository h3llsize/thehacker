package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SetNewSponsor extends MessageImpl {
    public SetNewSponsor(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {

    }
}
