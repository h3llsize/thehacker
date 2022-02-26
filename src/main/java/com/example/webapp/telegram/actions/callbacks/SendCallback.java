package com.example.webapp.telegram.actions.callbacks;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.ActionStorage;
import com.example.webapp.telegram.actions.handles.run.RunNewsletterHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SendCallback extends MessageImpl {
    private SendMessage sendMessage;

    public SendCallback(String name) {
        super(name);
    }

    public SendCallback(String name, SendMessage sendMessage, InlineKeyboardMarkup inlineKeyboardMarkup)
    {
        super(name);
        this.sendMessage = sendMessage;
        if(inlineKeyboardMarkup != null)
        {
            this.sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        initialize();
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        List<UserEntity> list =
                StreamSupport.stream(UserService.getAllUsers().spliterator(), false)
                        .collect(Collectors.toList());

        if (sendMessage != null) {
            new RunNewsletterHandler(list, sendMessage);
        }

        ActionStorage.removeMessage(name);
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(action.getCallbackQuery().getMessage().getMessageId());
        deleteMessage.setChatId(action.getCallbackQuery().getMessage().getChatId().toString());

        try {
            new Bot().execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
