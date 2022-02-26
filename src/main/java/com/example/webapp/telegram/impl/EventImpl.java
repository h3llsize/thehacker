package com.example.webapp.telegram.impl;

import com.example.webapp.telegram.actions.ActionStorage;
import com.example.webapp.telegram.entities.UserEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class EventImpl {
    private String name;
    public EventImpl(String name)
    {
        this.name = name;
        initialize();
    }

    public abstract void exec(Update update, UserEntity user, String params);

    public void initialize()
    {
        ActionStorage.addEvent(this, this.name);
    }

}
