package com.example.webapp.telegram.actions.keyboards.list;

import jnr.ffi.annotations.In;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MyAccountKeyboard extends InlineKeyboardMarkup {
    public MyAccountKeyboard() {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("\uD83D\uDCE4 История");
        inlineKeyboardButton.setCallbackData("numbers_history");

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\uD83D\uDD27 Информация");
        inlineKeyboardButton1.setCallbackData("myaccount_information");

        List<InlineKeyboardButton> list = new ArrayList<>();
        List<List<InlineKeyboardButton>> lists = new ArrayList<>();

        list.add(inlineKeyboardButton);
        list.add(inlineKeyboardButton1);

        lists.add(list);

        setKeyboard(lists);
    }
}
