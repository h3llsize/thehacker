package com.example.webapp.telegram.actions.keyboards;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public abstract class KeyboardImpl extends ReplyKeyboardMarkup {
    public KeyboardImpl() {
        setKeyboard(generateKeyboard());
    }

    protected abstract List<KeyboardRow> generateKeyboard();
}
