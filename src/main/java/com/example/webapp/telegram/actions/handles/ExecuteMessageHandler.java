package com.example.webapp.telegram.actions.handles;

import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.ActionStorage;
import com.example.webapp.telegram.actions.messages.SubscribeToChannelMessage;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.generators.UserGenerator;
import com.example.webapp.telegram.query.referral.ReferralQuery;
import com.example.webapp.telegram.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Locale;

@Component
public class ExecuteMessageHandler {

    public void execute(Update update, boolean callback) {
        UserEntity user;
        String messageText;

        if(update.hasMessage()) {
            if (update.getMessage().getText() != null) {
                messageText = update.getMessage().getText();
            } else {
                messageText = update.getMessage().getCaption();
            }
        } else
        {
            System.out.println("DATA : " + update.getCallbackQuery().getData());
            messageText = update.getCallbackQuery().getData();
        }

        if(!callback) {
            try {
                user = UserService.getUser(update);
            } catch (Exception e)
            {
                user = new UserGenerator(update).userEntity;
            }
        } else {
            try {
                user = UserService.getUser(update.getCallbackQuery().getMessage().getChatId());
            } catch (Exception e) {
                user = new UserGenerator(update).userEntity;
                Logger.sendConsole(e.toString());
            }
        }

        if(!isEnjoyed(user.getId()))
        {
            new SubscribeToChannelMessage().exec(user);
            return;
        }

        if(user.getEvent() != null && !callback)
        {
            ActionStorage.getEvent(user.getEvent()).exec(update,user,messageText);
            return;
        }

        if (messageText.startsWith("/")) {
            String command = messageText.split(" ")[0];

            if(user.isAdmin() && ActionStorage.adminHashMap.containsKey(command))
            {
                ActionStorage.getAdminCommand(command).exec(update,user);
                return;
            }

            if (ActionStorage.messageHashMap.containsKey(command)) {

                ActionStorage.getMessage(command).exec(update, user);

            } else {
                ActionStorage.getMessage("Unknown").exec(update, user);
            }
        }

        else {
            if (ActionStorage.messageHashMap.containsKey(messageText)) {
                ActionStorage.getMessage(messageText).exec(update, user);

            } else {
                ActionStorage.getMessage("Unknown").exec(update, user);
            }
        }
    }

    private boolean isEnjoyed(Long user)
    {
        GetChatMember chatMember = new GetChatMember();
        chatMember.setUserId(user);
        chatMember.setChatId(Bot.channel_id);

        GetChatMember chatMember1 = new GetChatMember();
        chatMember1.setUserId(user);
        chatMember1.setChatId(Bot.secondChannel_id);
        try {
            String userStatus = new Bot().execute(chatMember).getStatus();
            String secondStatus = new Bot().execute(chatMember1).getStatus();

            return !userStatus.equals("kicked") && !userStatus.equals("left") && !userStatus.equals("restricted") && !userStatus.equals("banned")
                    && !secondStatus.equals("kicked") && !secondStatus.equals("left") && !secondStatus.equals("restricted") && !secondStatus.equals("banned");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
