package com.example.webapp.telegram.query;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BotSponsor {
    public static String channelUrl;
    public static String message;

    @PostConstruct
    public void init() {
        channelUrl = "https://t.me/+MRsK7wwRpa42ZTUy";
        message = " ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖";
    }
}
