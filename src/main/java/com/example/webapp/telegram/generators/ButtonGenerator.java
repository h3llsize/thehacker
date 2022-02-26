package com.example.webapp.telegram.generators;

import jnr.ffi.annotations.In;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonGenerator {

    public ButtonGenerator() {

    }

    private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    public ButtonGenerator(String message, String buttonEvent, boolean event) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(message);
        if(event)
        {
            inlineKeyboardButton.setCallbackData(buttonEvent);
        }
        else
        {
            inlineKeyboardButton.setUrl(buttonEvent);
        }
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttonsRowList = new ArrayList<>();
        buttonsRow.add(inlineKeyboardButton);
        buttonsRowList.add(buttonsRow);
        this.inlineKeyboardMarkup.setKeyboard(buttonsRowList);
    }

    public InlineKeyboardMarkup generate(String message, String buttonEvent, boolean event)
    {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(message);
        if(event)
        {
            inlineKeyboardButton.setCallbackData(buttonEvent);
        }
        else
        {
            inlineKeyboardButton.setUrl(buttonEvent);
        }
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttonsRowList = new ArrayList<>();
        buttonsRow.add(inlineKeyboardButton);
        buttonsRowList.add(buttonsRow);
        this.inlineKeyboardMarkup.setKeyboard(buttonsRowList);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

}
