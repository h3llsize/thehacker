package com.example.webapp.telegram;

import com.example.webapp.telegram.actions.handles.ActionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;

@Component
public class Bot extends TelegramLongPollingBot {

    // Криво, но иначе архитектура с отправкой сообщения не работает :(
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.channel_id}")
    private String getChannel_id;

    @Value("${bot.channel_url}")
    private String getChannel_url;

    @Value("${bot.channel2_id}")
    private String getSecondChannel_id;

    @Value("${bot.channel2_url}")
    private String getSecondChannel_url;

    public static String token;
    public static String name;
    public static String channel_id;
    public static String channel_url;
    public static String secondChannel_url;
    public static String secondChannel_id;

    @PostConstruct
    private void init()
    {
        token = botToken;
        name = botName;
        channel_id = getChannel_id;
        channel_url = getChannel_url;
        secondChannel_id = getSecondChannel_id;
        secondChannel_url = getSecondChannel_url;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        try {
            ActionHandler.execute(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
