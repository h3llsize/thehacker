package com.example.webapp.telegram.actions.handles.run;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.entities.UserEntity;
import jnr.ffi.annotations.In;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.InputStream;
import java.util.List;

public class RunNewsletterHandler extends Thread {
    private final List<UserEntity> userEntities;
    private final SendMessage sendMessage;


    public RunNewsletterHandler(List<UserEntity> userEntities, SendMessage sendMessage) {
        this.userEntities = userEntities;
        this.sendMessage = sendMessage;
        start();
    }

    @Override
    public void run() {
        if(sendMessage != null)
        {
            userEntities.forEach(z -> {
                Logger.sendConsole("Message sent to " + z.getId());
                send(sendMessage, z);
            });
        }
    }

    private void send(SendMessage sendMessage, UserEntity userEntity)
    {
        try {
            sendMessage.setChatId(userEntity.getId().toString());
            sendMessage.enableHtml(true);
            new Bot().execute(sendMessage);
        } catch (Exception e)
        {
            Logger.sendConsole("Some exception with sending message to " + userEntity.getName() + "\n" +
                    e.getClass() + " : " + e.getMessage());
        }
    }
}
