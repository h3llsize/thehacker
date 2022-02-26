package com.example.webapp.telegram.actions.keyboards.list;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.messages.SubscribeToChannelMessage;
import com.example.webapp.telegram.query.BotSponsor;
import jnr.ffi.annotations.In;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SubscribeToChannelKeyboard extends InlineKeyboardMarkup {
    public SubscribeToChannelKeyboard(){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("⚙️ Ссылка на канал [Обязательно]");
        inlineKeyboardButton.setUrl(Bot.channel_url);

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setCallbackData("/start");
        inlineKeyboardButton1.setText("\uD83D\uDCA1 Я подписался");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("⚙️ Ссылка на 3 канал");
        inlineKeyboardButton2.setUrl(BotSponsor.channelUrl);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("⚙️ Ссылка на 2 канал [Обязательно]");
        inlineKeyboardButton3.setUrl(Bot.secondChannel_url);

        List<InlineKeyboardButton> list = new ArrayList<>();
        List<InlineKeyboardButton> list1 = new ArrayList<>();
        List<InlineKeyboardButton> list2 = new ArrayList<>();
        List<InlineKeyboardButton> list3 = new ArrayList<>();
        List<List<InlineKeyboardButton>> lists = new ArrayList<>();

        list.add(inlineKeyboardButton);
        list1.add(inlineKeyboardButton3);
        list2.add(inlineKeyboardButton1);
        list3.add(inlineKeyboardButton2);

        lists.add(list);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        setKeyboard(lists);
    }
}
